package com.sakura.forum.framework.web.service.syslogin;

import com.sakura.forum.core.domain.dto.SysUserLoginDto;

/**
 * 系统用户登入
 */
public interface SysLoginService {

    /**
     * 登入
     *
     * @param formData 参数
     * @return 响应结果
     */
    String login(SysUserLoginDto formData);
}
