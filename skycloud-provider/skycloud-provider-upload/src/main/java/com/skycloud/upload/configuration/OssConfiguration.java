package com.skycloud.upload.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云OSS系统配置参数
 *
 * @author sky
 */
@ConfigurationProperties(prefix = "oss")
public class OssConfiguration {


    @Getter
    @Setter
    private String clientId;

    @Getter
    @Setter
    private String clientSecret;

    @Getter
    @Setter
    private String endpoint;

    @Getter
    @Setter
    private String bucketName;

    @Getter
    @Setter
    private String aliyunAccessKeyId;

    @Getter
    @Setter
    private String aliyunAccessKeySecret;

    @Getter
    @Setter
    private String aliyunEndpoint;

    @Getter
    @Setter
    private String folderName;
}
