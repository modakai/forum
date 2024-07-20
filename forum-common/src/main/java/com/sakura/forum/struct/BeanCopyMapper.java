package com.sakura.forum.struct;

import com.sakura.forum.core.domain.dto.MenuSaveDto;
import com.sakura.forum.core.domain.entity.SysMenu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeanCopyMapper {

    BeanCopyMapper INSTANCE = Mappers.getMapper(BeanCopyMapper.class);

    SysMenu menuSaveDtoToSysMenu(MenuSaveDto sysMenuSaveDto);
}
