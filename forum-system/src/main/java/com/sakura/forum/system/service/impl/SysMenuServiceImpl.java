package com.sakura.forum.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.forum.constant.CommonConstant;
import com.sakura.forum.core.domain.dto.MenuRoleDto;
import com.sakura.forum.core.domain.dto.MenuSaveDto;
import com.sakura.forum.core.domain.entity.SysMenu;
import com.sakura.forum.core.domain.entity.SysUser;
import com.sakura.forum.core.domain.vo.Router;
import com.sakura.forum.core.domain.vo.RouterMeta;
import com.sakura.forum.enums.MenuTypeEnum;
import com.sakura.forum.enums.ResultCodeEnum;
import com.sakura.forum.exception.ServiceException;
import com.sakura.forum.struct.BeanCopyMapper;
import com.sakura.forum.system.mapper.SysMenuMapper;
import com.sakura.forum.system.service.ISysMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    private final SysMenuMapper menuMapper;

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

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveMenu(MenuSaveDto menuSaveDto) {
        // 1. 校验参数
        // 1.1 根据不同的菜单类型校验参数
        Integer menuType = menuSaveDto.getMenuType();
        // 0 1 2 必须参数  父级id 菜单名称 （唯一）、排序 菜单状态 使用 valid 框架
        // 1 必须填写 菜单名称、排序、路由地址、组件路径
        // 2 必须填写 菜单名称、排序、权限标识符
        if (Objects.equals(MenuTypeEnum.CATALOG.getValue(), menuType)) {
            // 目录
            if (StringUtils.isBlank(menuSaveDto.getPath())) {
                throw new ServiceException(ResultCodeEnum.DATA_ERROR.getCode(), "路由路径不能为空");
            }
            if (CommonConstant.MENU_PARENT_ID.equals(menuSaveDto.getParentId())) {
                // 设置顶级父级 组件Layout
                menuSaveDto.setComponent(CommonConstant.LAYOUT);
            }
        } else if (Objects.equals(MenuTypeEnum.NORMAL.getValue(), menuType)) {
            // 菜单
            if (StringUtils.isBlank(menuSaveDto.getPath()) || StringUtils.isBlank(menuSaveDto.getComponent())) {
                throw new ServiceException(ResultCodeEnum.DATA_ERROR.getCode(), "路由路径或组件路径不能为空");
            }
        } else if (Objects.equals(MenuTypeEnum.BUTTON.getValue(), menuType)) {
            // 按钮
            if (StringUtils.isBlank(menuSaveDto.getPerms())) {
                throw new ServiceException(ResultCodeEnum.DATA_ERROR.getCode(), "权限标识符不能为空");
            }
        }
        // 判断是否重复
        lambdaQuery()
                .eq(SysMenu::getMenuName, menuSaveDto.getMenuName())
                .oneOpt()
                .orElseThrow(() -> new ServiceException(ResultCodeEnum.DATA_ERROR.getCode(), "菜单名称重复"));

        // 2. 保存菜单
        // 类型转换
        SysMenu menu = BeanCopyMapper.INSTANCE.menuSaveDtoToSysMenu(menuSaveDto);
        menuMapper.insert(menu);
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
