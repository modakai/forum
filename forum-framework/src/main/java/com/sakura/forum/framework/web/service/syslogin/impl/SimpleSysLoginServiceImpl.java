package com.sakura.forum.framework.web.service.syslogin.impl;

import com.sakura.forum.core.domain.dto.SysUserLoginDto;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.exception.ServiceException;
import com.sakura.forum.framework.factory.captcha.CaptchaFactory;
import com.sakura.forum.framework.web.service.captcha.CaptchaService;
import com.sakura.forum.framework.web.service.syslogin.PasswordService;
import com.sakura.forum.framework.web.service.syslogin.SysLoginService;
import com.sakura.forum.security.StpKit;
import com.sakura.forum.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 * username + password 登录
 */
@Service("simpleLogin")
public class SimpleSysLoginServiceImpl implements SysLoginService {

    private final SysUserMapper sysUserMapper;
    private final PasswordService passwordService;
    private final CaptchaFactory captchaFactory;

    public SimpleSysLoginServiceImpl(SysUserMapper sysUserMapper, PasswordService passwordService, CaptchaFactory captchaFactory) {
        this.sysUserMapper = sysUserMapper;
        this.passwordService = passwordService;
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
            throw new ServiceException("用户不存在");
        }

        // 判断密码 这里的加密算法太慢了
        passwordService.validate(formData.getPassword(), sysUser.getPassword());

        // 判断状态
        if (sysUser.getStatus()) {
            throw new ServiceException("用户已封禁，请联系管理员");
        }

        // 进行登入
        StpKit.ADMIN.login(sysUser.getId());
        // 获取token
        String token = StpKit.ADMIN.getTokenValue();
        // 删除验证码
        captchaService.removeCaptcha(formData.getUsername());

        // 返回token
        return token;
    }


}
