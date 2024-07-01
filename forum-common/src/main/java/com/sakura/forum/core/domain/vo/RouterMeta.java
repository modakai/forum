package com.sakura.forum.core.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 前端路由组件的meta信息
 */
@Data
@Schema(description = "前端路由组件的meta信息")
public class RouterMeta {
    @Schema(description = "路由标题")
    private String title;
    @Schema(description = "路由icon图标")
    private String icon;
    @Schema(description = "菜单状态 0显示 1隐藏")
    private Boolean visible;
    @Schema(description = "是否外链 0否 1是")
    private Boolean isExternalLink;
}
