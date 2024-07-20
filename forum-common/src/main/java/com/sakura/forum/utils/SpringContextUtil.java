package com.sakura.forum.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SpringContextUtil implements ApplicationContextAware {

    //获取applicationContext
    // Spring 应用上下文环境
    @Getter
    private static ApplicationContext applicationContext;

    /**
     * 重写接口的方法,该方法的参数为框架自动加载的IOC容器对象
     * 该方法在启动项目的时候会自动执行,前提是该类上有IOC相关注解,例如@Component
     *
     * @param applicationContext ioc容器
     * @throws BeansException e
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 将框架加载的ioc赋值给全局静态ioc
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取对象
     *
     * @param name
     * @return Object 一个以所给名字注册的 bean 的实例（service 注解方式，自动生成以首字母小写的类名为 bean name）
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}