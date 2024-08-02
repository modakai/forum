package com.sakura.forum.exception.file;

import com.sakura.forum.enums.ResultCodeEnum;
import com.sakura.forum.exception.BaseException;

public class FileTypeErrorException extends BaseException {
    public FileTypeErrorException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum);
    }
}
