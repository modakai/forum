package com.sakura.forum.core.domain.dto;

import lombok.Data;

@Data
public class MenuRoleDto {
    private Long roleId;
    private Long menuId;
    private String menuPerms;
    private String roleKey;
}
