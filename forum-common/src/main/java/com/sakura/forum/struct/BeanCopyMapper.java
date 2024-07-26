package com.sakura.forum.struct;

import com.sakura.forum.core.LoginUser;
import com.sakura.forum.core.domain.dto.ChangeProfileDto;
import com.sakura.forum.core.domain.dto.MenuSaveDto;
import com.sakura.forum.core.domain.entity.SysMenu;
import com.sakura.forum.core.domain.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeanCopyMapper {

    BeanCopyMapper INSTANCE = Mappers.getMapper(BeanCopyMapper.class);

    /**
     * 菜单保存DTO 转换成 实体对象
     *
     * @param sysMenuSaveDto 菜单保存对象
     * @return 实体对象
     */
    SysMenu menuSaveDtoToSysMenu(MenuSaveDto sysMenuSaveDto);

    /**
     * 系统用户转换成登入用户对象
     *
     * @param user 系统用户
     * @return 登入用户对象
     */
    LoginUser sysUserToLoginUser(SysUser user);

    /**
     * 登入用户对象转换成系统用户
     *
     * @param loginUser 登入用户
     * @return 系统用户
     */
    SysUser loginUserToSysUser(LoginUser loginUser);

    /**
     * 修改资料DTO 转换成 系统用户
     *
     * @param dto
     */
    SysUser changeProfileDtoToSysUser(ChangeProfileDto dto);
}
