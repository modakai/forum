package com.sakura.forum.exception.user;

import com.sakura.forum.enums.ResultCodeEnum;
import com.sakura.forum.exception.BaseException;

public class UserPasswordNotMatchException extends BaseException {

    public UserPasswordNotMatchException() {
        super(ResultCodeEnum.OLD_PASSWORD_ERROR);
    }

}
