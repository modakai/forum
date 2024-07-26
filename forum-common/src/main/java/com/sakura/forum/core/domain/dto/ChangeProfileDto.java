package com.sakura.forum.core.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "修改用户信息")
@Data
public class ChangeProfileDto {

    @Schema(description = "昵称")
    private String nickName;
    @Schema(description = "性别")
    private Boolean gender;
    @Schema(description = "邮箱")
    private String phone;
}
