package com.sakura.forum.utils;

import cn.dev33.satoken.session.SaSession;
import com.sakura.forum.core.domain.entity.SysUser;
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


    public static final String SYS_USER_KEY = "sys_user";
    public static final String APP_USER_KEY = "app_user";

    public static SysUser getLoginSysUser() {
        // 如果这里拿到的是null 就会报错
        return (SysUser) Optional.ofNullable(StpKit.ADMIN.getSession().get(SYS_USER_KEY))
                .orElseThrow(() -> new ServiceException("用户信息为空"));
    }

    public static void cacheLoginUser(@NotNull Object user, Long id) {

        if (user == null)
            throw new ServiceException("用户不能为空");

        if (user instanceof SysUser) {
            SaSession session = StpKit.ADMIN.getSession();
            session.set(SYS_USER_KEY, user);
        }
    }
}
