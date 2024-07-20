package com.sakura.forum.core.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "菜单保存DTO")
public class MenuSaveDto {


    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    @Length(max = 40, message = "菜单名称长度不能超过40")
    private String menuName;
    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    @NotNull(message = "上级菜单不能为空")
    private Long parentId;
    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;
    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String component;
    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String perms;
    /**
     * 图标
     */
    @Schema(description = "图标")
    private String icon;
    /**
     * 菜单类型 0目录 1菜单 2按钮
     */
    @Schema(description = "菜单类型 0目录 1菜单 2按钮")
    private Integer menuType;
    /**
     * 菜单状态 0显示 1隐藏
     */
    @Schema(description = "菜单显示状态 0显示 1隐藏")
    private Boolean visible;
    /**
     * 菜单状态 0正常 1停用
     */
    @Schema(description = "菜单状态 0正常 1停用")
    @NotNull(message = "菜单状态不能为空")
    private Boolean status;
    /**
     * 顺序
     */
    @Schema(description = "顺序")
    @NotNull(message = "菜单顺序不能为空")
    private Integer sort;
    /**
     * 是否外链 0否 1是
     */
    @Schema(description = "是否外链 0否 1是")
    private Boolean isExternalLink;

    @Schema(description = "备注")
    private String remark;

}
