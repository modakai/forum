package com.sakura.forum.core;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登入用户
 *
 * @author modakai
 */
@Data
@Accessors(chain = true)
public class LoginUser {

    private Long id;
    private Long deptId;
    private String username;
    private String nickName;
    private String avatar;
    private String phone;
    private String loginType;
    private Boolean admin;
    private Boolean status;
    private Boolean gender;
}
