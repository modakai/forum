package com.sakura.forum.utils;

import com.sakura.forum.config.MinioConfig;
import com.sakura.forum.exception.ServiceException;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@RequiredArgsConstructor
@Getter
@Slf4j
public class MinioUtil {

    private final MinioConfig minioConfig;

    private final static String JOIN_STRING = "-";

    private MinioClient minioClient;

    @PostConstruct
    public void init() {
        minioClient = MinioClient.builder()
                .endpoint(minioConfig.getUrl())
                .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
                .build();
    }

    /**
     * 上传文件
     *
     * @param bucketName    bucket名
     * @param multipartFile 文件
     * @return 访问路径
     */
    public String uploadObject(String bucketName, MultipartFile multipartFile) {
        // 1 判断bucket是否存在 不存在就创建
        existsBucket(bucketName);

        // 2 上传文件
        try (InputStream inputStream = multipartFile.getInputStream()) {
            // 获取用户上传文件的名称
            String originalFilename = multipartFile.getOriginalFilename();
            String bucket = getBucketName(bucketName);
            log.info("文件名：{} bucket：{}", originalFilename, bucket);
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucket)
                    .contentType(multipartFile.getContentType())
                    .object(originalFilename)
                    .stream(inputStream, inputStream.available(), -1)
                    .build();

            // 上传
            minioClient.putObject(putObjectArgs);
            // 返回访问路径
            return minioConfig.getUrl() + "/" + bucket + "/" + originalFilename;
        } catch (Exception e) {
            log.error("上传发生异常：{}", e.getMessage(), e);
            throw new ServiceException(500, "上传失败");
        }
    }

    /**
     * 判断bucket是否存在
     */
    public void existsBucket(String bucketName) {
        try {
            // 判断 bucket 是否存在
            String bucket = getBucketName(bucketName);
            BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucket).build();
            if (!minioClient.bucketExists(bucketExistsArgs)) {
                // 创建 bucket
                MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder()
                        .bucket(bucket)
                        .build();
                minioClient.makeBucket(makeBucketArgs);
            }
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            throw new RuntimeException(e);
        }
    }

    private String getBucketName(String bucketName) {
        return minioConfig.getBaseBucket() + JOIN_STRING + bucketName;
    }
}
