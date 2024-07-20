package com.sakura.forum.enums;

import lombok.Getter;

/**
 * 菜单类型枚举
 * 菜单类型 0目录 1菜单 2按钮
 */
@Getter
public enum MenuTypeEnum {
    // 目录菜单
    CATALOG(0, "目录"),
    NORMAL(1, "菜单"),
    BUTTON(2, "按钮");

    private final Integer value;
    private final String label;

    MenuTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}
