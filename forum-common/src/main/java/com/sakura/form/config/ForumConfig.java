package com.sakura.form.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fourm")
@Data
public class ForumConfig {

    private String name;
    private String title;
    private String description;
    private String version;
    private String captchaType;
}
