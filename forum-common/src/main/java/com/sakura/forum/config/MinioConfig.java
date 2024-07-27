package com.sakura.forum.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "minio")
@Setter
@Getter
public class MinioConfig {

    private String host;
    private String url;
    private String baseBucket;
    private String accessKey;
    private String secretKey;
}
