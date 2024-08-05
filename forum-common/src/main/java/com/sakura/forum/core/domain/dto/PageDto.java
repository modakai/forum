package com.sakura.forum.core.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PageDto {

    @Schema(description = "页码", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer page;
    @Schema(description = "每页数量", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer size;
}
