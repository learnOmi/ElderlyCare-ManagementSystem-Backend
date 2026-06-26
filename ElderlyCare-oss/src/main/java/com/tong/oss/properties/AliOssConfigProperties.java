package com.tong.oss.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云 OSS 配置属性类
 *
 * @author Tong
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
@Configuration
@ConfigurationProperties(prefix = "oss.aliyun")
public class AliOssConfigProperties
{
    /** 地域节点（Endpoint） */
    private String endpoint;

    /** 访问密钥ID */
    private String accessKeyId;

    /** 访问密钥密钥 */
    private String accessKeySecret;

    /** 存储桶名称 */
    private String bucketName;

    /** 访问域名（不填则自动拼接 endpoint） */
    private String domain;
}
