package com.sakura.forum.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakura.forum.core.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Mapper
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
