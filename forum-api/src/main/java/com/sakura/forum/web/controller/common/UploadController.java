package com.sakura.forum.web.controller.common;

import cn.dev33.satoken.util.SaResult;
import com.sakura.forum.enums.BucketTypeEnum;
import com.sakura.forum.utils.MinioUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "上传接口")
@RequestMapping("/upload")
@RestController
public class UploadController {

    private final MinioUtil minioUtil;

    public UploadController(MinioUtil minioUtil) {
        this.minioUtil = minioUtil;
    }

    @Operation(summary = "图片上传")
    // 指定当前参数类型为文件上传
    @Parameter(name = "type", description = "bucket的类型；comment（评论）； avatar (头像)；...")
    @PostMapping("img")
    public SaResult uploadImg(@RequestPart("file") MultipartFile file, @RequestParam("type") String type) {
        // 校验类型
        BucketTypeEnum.validate(type);

        // 上传图片
        String url = minioUtil.uploadImage(type, file);
        // 响应访问地址
        return SaResult.data(url);
    }
}
