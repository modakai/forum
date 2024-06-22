package com.sakura.forum.core.domain.entity;

import com.sakura.forum.core.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Schema(name = "系统角色", description = "系统角色")
@Data
public class SysRole extends BaseEntity {
    private Long id;
    private String roleName;
    private String roleKey;
    private Integer sort;
    private Boolean menuCheckStrictly;
    private Boolean status;
}
