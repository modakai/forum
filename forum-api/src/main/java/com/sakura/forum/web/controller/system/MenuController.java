package com.sakura.forum.web.controller.system;

import cn.dev33.satoken.util.SaResult;
import com.sakura.forum.core.BaseController;
import com.sakura.forum.core.domain.dto.MenuQueryDto;
import com.sakura.forum.core.domain.dto.MenuSaveDto;
import com.sakura.forum.core.domain.entity.SysMenu;
import com.sakura.forum.framework.web.service.permission.SysPermissionService;
import com.sakura.forum.system.service.ISysMenuService;
import com.sakura.forum.utils.LoginUserUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "菜单管理")
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

    private final ISysMenuService menuService;
    private final SysPermissionService permissionService;

    public MenuController(ISysMenuService menuService, SysPermissionService permissionService) {
        this.menuService = menuService;
        this.permissionService = permissionService;
    }

    @Operation(summary = "搜索树形菜单列表")
    @GetMapping("tree")
    public SaResult searchMenuTreeList(@ParameterObject MenuQueryDto query) {

        // 1 获取用户id
        Long userId = LoginUserUtil.getLoginSysUserId();
        // 2 获取菜单列表
        List<SysMenu> tree = menuService.treeList(query, userId);
        return SaResult.data(tree);
    }

    @Operation(summary = "更新菜单")
    @PutMapping("update")
    public SaResult updateMenu(@RequestBody SysMenu formData) {
        if (formData.getId() == null)
            return fail("菜单不存在");
        // 更新
        menuService.updateById(formData);

        SysMenu menu = new SysMenu();
        menu.setId(formData.getId());
        // 查询 roleKey
        List<String> roleKeyList = menuService.findMenuRelationRoleKeyList(List.of(menu));
        // 删除对应的权限缓存
        permissionService.removeCacheRolePerms(roleKeyList);
        return success("更新成功");
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("delete")
    public SaResult removeMenu(Long id) {
        // 删除当前菜单的id 以及下级列表的id 还有角色和菜单管理表的id
        // 如果校验 当前的菜单是否属于当前用户管理的菜单列表
        List<String> roleKeyList = menuService.removeMenu(id);
        // 删除缓存
        permissionService.removeCacheRolePerms(roleKeyList);
        return SaResult.ok();
    }

    @Operation(summary = "保存菜单")
    @PostMapping("save")
    public SaResult saveMenu(@RequestBody @Validated MenuSaveDto menuSaveDto) {
        menuService.saveMenu(menuSaveDto);
        return SaResult.ok();
    }
}
