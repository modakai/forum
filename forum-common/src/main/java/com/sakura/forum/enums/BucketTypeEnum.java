package com.sakura.forum.enums;

import lombok.Getter;

@Getter
public enum BucketTypeEnum {
    // 评论 bucket
    BUCKET_COMMENT("comment");

    private final String type;

    BucketTypeEnum(String type) {
        this.type = type;
    }

    public static void validate(String type) {
        for (BucketTypeEnum bucketTypeEnum : BucketTypeEnum.values()) {
            if (bucketTypeEnum.getType().equals(type)) {
                return;
            }
        }

        throw new RuntimeException("类型错误");
    }
}
