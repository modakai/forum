package com.sakura.forum.system.mapper;

import com.sakura.forum.core.domain.dto.MenuRoleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统菜单Mapper
 */
@Mapper
public interface SysMenuMapper {

    /**
     * 获取用户菜单权限
     *
     * @param id 用户id
     * @return 返回菜单权限列表
     */
    List<MenuRoleDto> selectMenuPerms(Long id);
}
