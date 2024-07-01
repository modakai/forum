package com.sakura.forum.constant;

public class RedisCacheConstant {

    /**
     * 验证码缓存key前缀
     */
    public static final String CAPTCHA_KEY = "captcha:";

    /**
     * 角色缓存key
     */
    public static final String ROLE_KEY = "role:";

    /**
     * 角色拥有的权限缓存key
     */
    public static final String PERMISSION_KEY = "role:perms";
}
