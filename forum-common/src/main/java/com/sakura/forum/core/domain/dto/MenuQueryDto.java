package com.sakura.forum.core.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "菜单查询对象")
public class MenuQueryDto {


    @Schema(description = "菜单名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String menuName;

    @Schema(description = "菜单状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean status;
}
