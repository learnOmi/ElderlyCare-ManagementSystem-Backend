package com.tong.oss.client;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.tong.oss.properties.AliOssConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 阿里云 OSS 文件存储服务
 * <p>
 * 提供文件上传与删除能力，所有文件公开读权限。
 *
 * @author Tong
 */
@Slf4j
@Component
public class OSSAliyunFileStorageService
{
    @Autowired
    private OSS ossClient;

    @Autowired
    private AliOssConfigProperties aliOssConfigProperties;

    /**
     * 上传文件流到 OSS
     *
     * @param objectName  OSS 存储对象名（通常为 UUID.后缀）
     * @param inputStream 文件输入流
     * @return 文件访问 URL（全路径），上传失败返回 null
     */
    public String store(String objectName, InputStream inputStream)
    {
        if (inputStream == null)
        {
            log.error("OSS 上传失败：文件流为空，objectName={}", objectName);
            return null;
        }

        log.info("OSS 文件上传开始：{}", objectName);
        try
        {
            String bucketName = aliOssConfigProperties.getBucketName();
            PutObjectRequest request = new PutObjectRequest(bucketName, objectName, inputStream);
            PutObjectResult result = ossClient.putObject(request);
            // 设置桶公开读权限
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

            if (result != null)
            {
                log.info("OSS 文件上传成功：{}", objectName);
            }
        }
        catch (OSSException oe)
        {
            log.error("OSS 上传错误：{}", oe.getMessage(), oe);
        }
        catch (ClientException ce)
        {
            log.error("OSS 客户端错误：{}", ce.getMessage(), ce);
        }

        // 拼接访问 URL
        String domain = aliOssConfigProperties.getDomain();
        if (domain == null || domain.isBlank())
        {
            domain = "https://" + aliOssConfigProperties.getBucketName() + "." + aliOssConfigProperties.getEndpoint();
        }
        return domain + "/" + objectName;
    }

    /**
     * 根据完整 URL 删除 OSS 文件
     *
     * @param pathUrl 文件全路径 URL
     */
    public void delete(String pathUrl)
    {
        String prefix = aliOssConfigProperties.getDomain();
        if (prefix == null || prefix.isBlank())
        {
            prefix = "https://" + aliOssConfigProperties.getBucketName() + "." + aliOssConfigProperties.getEndpoint();
        }

        String key = pathUrl.replace(prefix + "/", "");
        List<String> keys = new ArrayList<>();
        keys.add(key);

        log.info("OSS 文件删除：key={}", key);
        ossClient.deleteObjects(new DeleteObjectsRequest(aliOssConfigProperties.getBucketName()).withKeys(keys));
    }
}
