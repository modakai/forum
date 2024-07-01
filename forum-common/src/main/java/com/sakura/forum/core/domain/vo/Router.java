package com.sakura.forum.core.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 前端的路由组件
 */
@Data
@Schema(description = "前端路由组件")
public class Router {
    @Schema(description = "路由名称")
    private String name;
    @Schema(description = "访问路径")
    private String path;
    @Schema(description = "组件")
    private String component;
    @Schema(description = "meta信息")
    private RouterMeta meta;
    @Schema(description = "子列表")
    private List<Router> children;
}
