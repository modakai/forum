package com.sakura.forum.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sakura.forum.core.pojo.SysUser;

public interface ISysUserService extends IService<SysUser> {
    SysUser demo(String username);
}
