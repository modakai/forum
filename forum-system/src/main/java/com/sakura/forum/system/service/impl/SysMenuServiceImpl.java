package com.sakura.forum.system.service.impl;

import com.sakura.forum.constant.CommonConstant;
import com.sakura.forum.core.domain.dto.MenuRoleDto;
import com.sakura.forum.core.domain.entity.SysMenu;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.core.domain.vo.Router;
import com.sakura.forum.core.domain.vo.RouterMeta;
import com.sakura.forum.system.mapper.SysMenuMapper;
import com.sakura.forum.system.service.ISysMenuService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Resource
    private SysMenuMapper menuMapper;

    @Override
    public List<MenuRoleDto> searchMenuPerms(SysUser user) {
        return menuMapper.selectMenuPerms(user.getId());
    }

    @Override
    public List<SysMenu> searchMenuTreeList(Long userId) {
        List<SysMenu> menuList = menuMapper.selectMenuListByUserId(userId);
        return getChildren(menuList, CommonConstant.MENU_PARENT_ID);
    }

    /**
     * 构建前端路由
     *
     * @param menuList 菜单列表
     * @return 菜单路由
     */
    @Override
    public List<Router> buildRouters(List<SysMenu> menuList) {

        List<Router> routers = new LinkedList<>();

        for (SysMenu menu : menuList) {
            Router router = new Router();

            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));

            RouterMeta meta = new RouterMeta();
            meta.setTitle(menu.getMenuName());
            meta.setIcon(menu.getIcon());
            meta.setVisible(menu.getVisible());
            meta.setIsExternalLink(menu.getIsExternalLink());

            router.setMeta(meta);

            router.setChildren(buildRouters(menu.getChildren()));

            routers.add(router);
        }

        return routers;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param menuList 列表
     * @param parentId 父节点
     * @return 列表
     */
    public List<SysMenu> getChildren(List<SysMenu> menuList, Long parentId) {
        List<SysMenu> children = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menu.getParentId().equals(parentId)) {
                // 找到直接子节点后，递归查找其子节点
                List<SysMenu> subChildren = getChildren(menuList, menu.getId());
                menu.setChildren(subChildren);
                children.add(menu);
            }
        }
        return children;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenu menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(SysMenu menu) {
        return menu.getParentId().intValue() == 0 && CommonConstant.TYPE_MENU.equals(menu.getMenuType())
                && CommonConstant.IS_EXTERNAL_LINK_NO.equals(menu.getIsExternalLink());

    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu) {
        String routerPath = menu.getPath();
//        // 内链打开外网方式
//        if (menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
//            routerPath = innerLinkReplaceEach(routerPath);
//        }

        // 非外链并且是一级目录（类型为目录）
        if (CommonConstant.MENU_PARENT_ID == menu.getParentId().intValue() && CommonConstant.TYPE_DIR.equals(menu.getMenuType())
                && CommonConstant.IS_EXTERNAL_LINK_NO.equals(menu.getIsExternalLink())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenu menu) {
        String component = CommonConstant.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } /*else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            component = UserConstants.INNER_LINK;
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = UserConstants.PARENT_VIEW;
        }*/
        return component;
    }

}
