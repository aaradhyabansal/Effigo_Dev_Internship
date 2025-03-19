package com.effigo.POC.AWS;

import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

@Slf4j
@Service
public class S3BucketSizeChecker {
    @Value("${aws.s3.bucket-name}")
    private String bucketName;
    @Value("${filesize}")
    private Long bucketSize;

    public boolean isBucketSizeExceeded(S3Client s3Client) {
        log.info("AChecking if bucket size is exceeded");
        log.info("Bucket name: " + bucketName);
        ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucketName).build();
        ListObjectsV2Response response = s3Client.listObjectsV2(request);
        log.info("BChecking if bucket size is exceeded");
        long totalSize=response.contents().stream().mapToLong(S3Object::size).sum();
        log.info("totalSize: " + totalSize);
        return totalSize > bucketSize;
    }



}
