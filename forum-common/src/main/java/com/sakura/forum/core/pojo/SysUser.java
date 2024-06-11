package com.sakura.forum.core.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sakura.forum.core.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统用户
 *
 * @author modakai
 */
@Schema(description = "系统用户")
@TableName("sys_user")
@Getter
@Setter
@ToString
public class SysUser extends BaseEntity {

    private Long id;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电话
     */
    private String phone;

    /**
     * 性别 ( 0(false) 男  1 (true)女 )
     */
    private Boolean gender;

    /**
     * 状态 ( 0(false) 正常  1 (true)禁用 )
     */
    private Boolean status;


}
