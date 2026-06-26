package com.tong.oss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.SetBucketLoggingRequest;
import com.tong.oss.properties.AliOssConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云 OSS 客户端自动配置
 * <p>
 * 启动时自动创建 OSS 客户端 Bean，若存储桶不存在则自动创建。
 *
 * @author Tong
 */
@Slf4j
@Configuration
public class OssAliyunAutoConfig
{
    @Autowired
    private AliOssConfigProperties aliOssConfigProperties;

    /**
     * 创建 OSS 客户端 Bean
     *
     * @return OSS 客户端实例
     */
    @Bean
    public OSS ossClient()
    {
        log.info("========== 开始创建 OSS Client ==========");

        OSS ossClient = new OSSClientBuilder().build(
                aliOssConfigProperties.getEndpoint(),
                aliOssConfigProperties.getAccessKeyId(),
                aliOssConfigProperties.getAccessKeySecret()
        );

        // 判断存储桶是否存在，不存在则自动创建
        if (!ossClient.doesBucketExist(aliOssConfigProperties.getBucketName()))
        {
            ossClient.createBucket(aliOssConfigProperties.getBucketName());
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(aliOssConfigProperties.getBucketName());
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
            log.info("OSS 存储桶 [{}] 创建成功", aliOssConfigProperties.getBucketName());
        }

        // 配置访问日志
        SetBucketLoggingRequest loggingRequest = new SetBucketLoggingRequest(aliOssConfigProperties.getBucketName());
        loggingRequest.setTargetBucket(aliOssConfigProperties.getBucketName());
        loggingRequest.setTargetPrefix(aliOssConfigProperties.getBucketName());
        ossClient.setBucketLogging(loggingRequest);

        log.info("========== OSS Client 创建完成 ==========");
        return ossClient;
    }
}
