package com.sakura.forum.utils;

import com.sakura.forum.core.LoginUser;
import com.sakura.forum.exception.ServiceException;
import com.sakura.forum.security.StpKit;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

/**
 * 获取登入用户工具类
 *
 * @author modakai
 */
public class LoginUserUtil {

    public static final String SYS_USER_TYPE = StpKit.ADMIN_TYPE;

    public static final String APP_USER_TYPE = StpKit.USER_TYPE;


    private static final String CACHE_PREFIX = "userinfo:";

    private static final String SYS_USER_KEY = CACHE_PREFIX + "sys:user:";

    private static final String APP_USER_KEY = CACHE_PREFIX + "app:user:";

    private static final RedisUtil redisUtil = SpringContextUtil.getBean(RedisUtil.class);


    public static LoginUser getLoginSysUser() {
        // 先拿到id
        var loginId = StpKit.ADMIN.getLoginId();
        // 再拿数据
        return (LoginUser) Optional
                .ofNullable(redisUtil.getCacheObject(SYS_USER_KEY + loginId))
                .orElseThrow(() -> new ServiceException("用户信息为空"));
    }

    /**
     * 获取系统登入用户的id
     *
     * @return 用户id
     */
    public static Long getLoginSysUserId() {
        return Long.parseLong((String) StpKit.ADMIN.getLoginId());
    }

    /**
     * 缓存用户数据
     *
     * @param user 登入用户
     */
    public static void cacheLoginUser(@NotNull LoginUser user) {
        if (user == null)
            throw new ServiceException("用户不能为空");

        String loginType = user.getLoginType();

        if (loginType == null)
            throw new ServiceException("用户类型不能为空");

        String key;
        // 直接缓存
        if (SYS_USER_TYPE.equals(loginType)) {
            // 使用redis缓存
            key = SYS_USER_KEY + user.getId();

        } else if (APP_USER_TYPE.equals(loginType)) {
            key = APP_USER_KEY + user.getId();

        } else {
            throw new ServiceException("用户类型错误");
        }

        // 缓存数据
        redisUtil.setCacheObject(key, user);
    }
}
