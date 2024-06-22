package com.sakura.forum.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sakura.forum.core.domain.entity.SysUser;

public interface ISysUserService extends IService<SysUser> {

    /**
     * 获取用户信息
     *
     * @param id 用户的id
     * @return 用户的信息
     */
    SysUser searchUserInfo(long id);
}
