package com.sakura.forum.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "forum")
@Data
public class ForumConfig {

    private String name;
    private String title;
    private String description;
    private String version;
}
