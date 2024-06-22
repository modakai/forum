package com.sakura.forum.framework.config;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.stp.StpUtil;
import com.sakura.forum.security.StpKit;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    /**
     * 用于实现多用表多登入的情况
     */
    @PostConstruct
    public void setSaTokenConfig() {
        // 设定 StpUtil 使用的 SaTokenConfig 配置参数对象
        SaTokenConfig config1 = new SaTokenConfig();
        config1.setTokenName("ADMIN");
        config1.setTokenPrefix("Bearer");
        config1.setActiveTimeout(-1);
        config1.setIsConcurrent(true);
        config1.setIsShare(true);
        config1.setTimeout(200000);
        config1.setTokenStyle("tik");
        config1.setIsLog(true);
        // 更多设置 ...
        StpKit.ADMIN.setConfig(config1);
        StpUtil.stpLogic.setConfig(config1);
    }


//    private final List<String> excludePathPatterns = List.of(
//            "/system/login", "/system/captcha", "/doc.html", "/webjars/**",
//            "/favicon.ico"
//    );
//
//    // 注册拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
//        systemInterceptor(registry, StpKit.ADMIN, "/system/**");
//
//        systemInterceptor(registry, StpKit.USER, "/app/**");
//    }
//
//    private void systemInterceptor(InterceptorRegistry registry, StpLogic admin, String... x) {
//        registry.addInterceptor(new SaInterceptor(handle -> admin.checkLogin()))
//                .addPathPatterns(x)
//                .excludePathPatterns(excludePathPatterns);
//    }
}
