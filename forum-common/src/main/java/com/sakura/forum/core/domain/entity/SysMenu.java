package com.sakura.forum.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sakura.forum.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 系统菜单表
 *
 * @TableName sys_menu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class SysMenu extends BaseEntity implements Serializable {

    /**
     *
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 父菜单ID
     */
    private Long parentId;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 图标
     */
    private String icon;
    /**
     * 菜单类型 0目录 1菜单 2按钮
     */
    private Integer menuType;
    /**
     * 菜单状态 0显示 1隐藏
     */
    private Boolean visible;
    /**
     * 菜单状态 0正常 1停用
     */
    private Boolean status;
    /**
     * 顺序
     */
    private Integer sort;
    /**
     * 是否外链 0否 1是
     */
    private Boolean isExternalLink;

    @TableField(exist = false)
    private List<SysMenu> children;

}
