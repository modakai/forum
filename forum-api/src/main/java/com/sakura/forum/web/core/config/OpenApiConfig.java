package com.sakura.forum.web.core.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                // 配置信息
                .info(apiInfo());
    }


    /**
     * 以下分组和资源映射都可省略
     */
    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder().group("System系统模块")
                .pathsToMatch("/system/**")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Operation.class))
                .build();
    }


    public Info apiInfo() {
        return new Info()
                .title("校园论坛系统")
                .description("论坛系统接口文档")
                .version("1.0.0")
                .contact(
                        new Contact().name("modakai").email("2049202552@qq.com")
                );
    }
}
