package com.sakura.forum.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakura.forum.core.domain.dto.MenuQueryDto;
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

    /**
     * 搜索菜单列表
     *
     * @param query  搜索条件
     * @param userId 用户id
     * @return 返回的数据
     */
    List<SysMenu> selectMenuList(@Param("query") MenuQueryDto query, @Param("userId") Long userId);

    /**
     * 根据菜单id和用户id获取菜单列表
     *
     * @param id     菜单id
     * @param userId 用户id
     * @return 菜单列表
     */
    List<SysMenu> selectMenuListByMenuIdAndUserId(@Param("menuId") Long id, @Param("userId") Long userId);

    /**
     * 删除数据
     *
     * @param menuList 列表
     */
    void deleteBatch(@Param("list") List<SysMenu> menuList);

    /**
     * 删除关联表数据
     *
     * @param menuList 列表
     */
    void deleteBatchRelationRole(List<SysMenu> menuList);

    /**
     * 获取菜单对应的角色列表
     *
     * @param menuList 菜单列表
     * @return 角色key列表
     */
    List<String> selectMenuRelationRoleKeyList(List<SysMenu> menuList);
}
