package com.sakura.forum.enums;

import lombok.Getter;

@Getter
public enum FileImageEnum {

    PNG("png"),
    JPG("jpg"),
    JPEG("jpeg");

    private final String value;

    FileImageEnum(String value) {
        this.value = value;
    }

    public static boolean isImage(String fileType) {
        for (FileImageEnum fileImageEnum : FileImageEnum.values()) {
            if (fileImageEnum.getValue().equals(fileType)) {
                return true;
            }
        }
        return false;
    }

}