package com.effigo.POC.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;
    public String uploadRegistrationFile(File file, String bucketName) throws Exception {
        String fileName="effigo/registration/"+file.getName();
        log.info("Uploading file "+fileName+" to "+bucketName);
        PutObjectRequest putRequest=PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType("application/pdf")
                .build();

        s3Client.putObject(putRequest, RequestBody.fromFile(file));
        return "https://s3.amazonaws.com/" + bucketName + "/" + fileName;

    }
    public String uploadCertificationFile(File file, String bucketName) throws Exception {
        String fileName="effigo/certification/"+file.getName();
        log.info("Uploading file "+fileName+" to "+bucketName);
        PutObjectRequest putRequest=PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType("application/pdf")
                .build();

        s3Client.putObject(putRequest, RequestBody.fromFile(file));
        return "https://s3.amazonaws.com/" + bucketName + "/" + fileName;

    }
    public void saveFileMetaData(DynamoDbClient dynamoDbClient,String fileName,String s3Key,String bucketName,long timeStamp) throws Exception {
        Map<String, AttributeValue> item=new HashMap<>();

        item.put("s3_key",AttributeValue.builder().s(s3Key).build());
        item.put("bucket_name",AttributeValue.builder().s(bucketName).build());
        item.put("time_stamp",AttributeValue.builder().s(String.valueOf(timeStamp)).build());

        PutItemRequest putItemRequest=PutItemRequest.builder()
                .tableName("FileMetadata")
                .item(item)
                .build();

        dynamoDbClient.putItem(putItemRequest);
    }
    public List<S3Object> searchFilesInS3(String bucketName, String keyword, int pageSize, String continuationToken)
    {
        ListObjectsV2Request.Builder requestBuilder=ListObjectsV2Request.builder()
                .bucket(bucketName)
                .maxKeys(pageSize)
                .prefix(keyword);

        if(continuationToken!=null){
            requestBuilder.continuationToken(continuationToken);
        }

        ListObjectsV2Response response=s3Client.listObjectsV2(requestBuilder.build());
        log.info("✅ Found " + response.contents().size() + " files in " + bucketName);

        return response.contents();
    }

    public ResponseEntity<Resource>downloadFile(String bucketName,String key)
    {
        GetObjectRequest getObjectRequest=GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        ResponseInputStream<?> s3Object=s3Client.getObject(getObjectRequest);
        InputStreamResource resource=new InputStreamResource( s3Object );
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + key.substring(key.lastIndexOf("/") + 1))
                .body(resource);
    }
}
