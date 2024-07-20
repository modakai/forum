package com.sakura.forum.web.controller.system;

import cn.dev33.satoken.util.SaResult;
import com.sakura.forum.core.domain.dto.MenuSaveDto;
import com.sakura.forum.system.service.ISysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "菜单管理")
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    private final ISysMenuService menuService;

    public MenuController(ISysMenuService menuService) {
        this.menuService = menuService;
    }

    @Operation(summary = "保存菜单")
    @PostMapping("save")
    public SaResult saveMenu(@RequestBody @Validated MenuSaveDto menuSaveDto) {
        menuService.saveMenu(menuSaveDto);
        return SaResult.ok();
    }
}
