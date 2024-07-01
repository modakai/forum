package com.sakura.forum.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakura.forum.core.domain.dto.MenuRoleDto;
import com.sakura.forum.core.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统菜单Mapper
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 获取用户菜单权限
     *
     * @param id 用户id
     * @return 返回菜单权限列表
     */
    List<MenuRoleDto> selectMenuPerms(Long id);

    /**
     * 获取系统用户菜单列表
     *
     * @param userId 用户id
     * @return 返回列表
     */
    List<SysMenu> selectMenuListByUserId(@Param("userId") Long userId);
}
