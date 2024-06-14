package com.sakura.forum.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakura.forum.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户Mapper
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名或手机号查询用户
     *
     * @param loginType 登入类型 normal（账号密码登入）；sms（短信验证码登入）
     * @param username  参数
     * @return 查询到的信息
     */
    SysUser selectSysUserByUsernameOrPhone(@Param("loginType") String loginType, @Param("username") String username);
}
