package com.sakura.forum.framework.web.service.syslogin.impl;

import com.sakura.forum.core.LoginUser;
import com.sakura.forum.core.domain.dto.SysUserLoginDto;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.enums.ResultCodeEnum;
import com.sakura.forum.exception.ServiceException;
import com.sakura.forum.framework.factory.captcha.CaptchaFactory;
import com.sakura.forum.framework.web.service.captcha.CaptchaService;
import com.sakura.forum.framework.web.service.syslogin.SysLoginService;
import com.sakura.forum.security.StpKit;
import com.sakura.forum.struct.BeanCopyMapper;
import com.sakura.forum.system.mapper.SysUserMapper;
import com.sakura.forum.utils.LoginUserUtil;
import com.sakura.forum.utils.PasswordUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * username + password 登录
 */
@Service("simpleLogin")
public class SimpleSysLoginServiceImpl implements SysLoginService {

    private final SysUserMapper sysUserMapper;
    private final CaptchaFactory captchaFactory;

    public SimpleSysLoginServiceImpl(SysUserMapper sysUserMapper, CaptchaFactory captchaFactory) {
        this.sysUserMapper = sysUserMapper;
        this.captchaFactory = captchaFactory;
    }

    @Override
    public String login(SysUserLoginDto formData) {

        // 验证码校验
        CaptchaService captchaService = captchaFactory.createCaptchaGenerator(formData.getLoginType());
        captchaService.validateCaptcha(formData.getKey(), formData.getCaptcha());

        String loginType = formData.getLoginType();
        // 查询用户信息
        SysUser sysUser = sysUserMapper.selectSysUserByUsernameOrPhone(loginType, formData.getUsername());
        // 是否不存在对应的用户
        if (sysUser == null) {
            throw new ServiceException(ResultCodeEnum.USER_IS_NOT_EXIST);
        }

        // 判断密码
        PasswordUtil.validate(formData.getPassword(), sysUser.getPassword());

        // 判断状态
        if (sysUser.getStatus()) {
            throw new ServiceException("用户已封禁，请联系管理员");
        }

        // 缓存用户信息
        // 转换对象
        CompletableFuture.runAsync(() -> {
            LoginUser loginUser = BeanCopyMapper.INSTANCE.sysUserToLoginUser(sysUser);
            loginUser.setLoginType(LoginUserUtil.SYS_USER_TYPE);
            LoginUserUtil.cacheLoginUser(loginUser);
        });

        // 进行登入
        StpKit.ADMIN.login(sysUser.getId(), formData.getRememberMe());

        // 获取token
        String token = StpKit.ADMIN.getTokenValue();

        // 删除验证码 可以使用异步进行删除
        CompletableFuture.runAsync(() -> {
            captchaService.removeCaptcha(formData.getUsername());
        });

        // 返回token
        return token;
    }


}
