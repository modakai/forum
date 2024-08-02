package com.sakura.forum.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sakura.forum.core.domain.dto.ChangePasswordDto;
import com.sakura.forum.core.domain.dto.ChangeProfileDto;
import com.sakura.forum.core.domain.entity.SysUser;

public interface ISysUserService extends IService<SysUser> {

    /**
     * 获取用户信息
     *
     * @param id 用户的id
     * @return 用户的信息
     */
    SysUser searchUserInfo(long id);

    /**
     * 修改密码
     *
     * @param changePasswordDto 修改密码的实体
     */
    void changePassword(ChangePasswordDto changePasswordDto);

    /**
     * 修改用户信息
     *
     * @param form 修改信息的数据
     */
    void changeProfile(ChangeProfileDto form);

    /**
     * 修改用户头像
     *
     * @param avatarUrl 用户头像的url
     */
    void changeAvatar(String avatarUrl);
}
