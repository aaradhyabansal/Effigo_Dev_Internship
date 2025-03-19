package com.effigo.POC.AWS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

@Service
public class S3DataLakeMover {

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.s3.bucket-datalake}")
    private String bucketDataLake;

    public void moveToDataLake(S3Client s3Client) {
        ListObjectsV2Request request=ListObjectsV2Request.builder().bucket(bucketName).build();
        ListObjectsV2Response response=s3Client.listObjectsV2(request);

        for(S3Object s3Object:response.contents()) {
            String objectKey=s3Object.key();
            CopyObjectRequest copyObjectRequest=CopyObjectRequest.builder()
                    .sourceBucket(bucketName)
                    .destinationBucket(bucketDataLake)
                    .sourceKey(objectKey)
                    .destinationKey(objectKey)
                    .build();

            s3Client.copyObject(copyObjectRequest);

            DeleteObjectRequest deleteObjectRequest=DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .build();
            s3Client.deleteObject(deleteObjectRequest);
            System.out.println("Moved " + objectKey + " to " + bucketDataLake);
        }

    }
}
