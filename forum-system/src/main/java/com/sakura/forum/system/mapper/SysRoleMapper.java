package com.sakura.forum.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    /**
     * 获取关联的角色
     *
     * @param id 用户id
     * @return 返回角色列表
     */
    List<String> selectRolePerms(@Param("userId") Long id);
}
