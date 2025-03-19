package com.effigo.POC.Service;

import com.effigo.POC.DTO.FileMetadataDTO;
import com.effigo.POC.DTO.S3ObjectDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    @Value("${aws.s3.bucket-name}")
    String mainBucket;
    @Value("${aws.s3.bucket-datalake}")
    String dataLakeBucket;
    @Value("${aws.s3.dynamo-tablename}")
            String dynamoTableName;

    @Autowired
    S3Service s3Service;
    @Autowired
    PaginationService paginationService;
    @Autowired
    DynamoDbClient dynamoDbClient;
    @Autowired
    DynamoService dynamoService;
    public List<S3ObjectDTO> searchAcrossBucket(S3Client s3Client, String keyword, int pageSize, String continuationToken) {
        log.info("searchAcrossBucket start");
        FileMetadataDTO metadata=dynamoService.searchFilesInDynamoDb(dynamoDbClient,dynamoTableName,keyword);
        log.info("searchAcrossBucket end");
        log.info("searchAcrossBucket result: {}", metadata);
        if(metadata!=null){
            log.info("✅ File found in DynamoDB! Searching in: " + metadata.getBucketName());
            System.out.println(paginationService.searchFilesinBucket(s3Client,metadata.getBucketName(),keyword,pageSize,continuationToken));
            return paginationService.searchFilesinBucket(s3Client,metadata.getBucketName(),keyword,pageSize,continuationToken);
//            return s3Service.searchFilesInS3(metadata.getBucketName(),keyword,pageSize,continuationToken);
        }

        return null;

    }
    public ResponseEntity<Resource> searchAcrossBucketAndDownload(S3Client s3Client, String keyword, int pageSize, String continuationToken) {
        log.info("searchAcrossBucketD start");
        FileMetadataDTO metadata=dynamoService.searchFilesInDynamoDb(dynamoDbClient,dynamoTableName,keyword);
        log.info("searchAcrossBucketD end");
        log.info("searchAcrossBucketD result: {}", metadata);
        if(metadata!=null){
            log.info("✅ File found in DynamoDB! Searching in: " + metadata.getBucketName());
//            System.out.println(paginationService.searchFilesinBucket(s3Client,metadata.getBucketName(),keyword,pageSize,continuationToken));
            return paginationService.searchFilesinBucketAndDownload(s3Client,metadata.getBucketName(),keyword,pageSize,continuationToken);
//            return s3Service.searchFilesInS3(metadata.getBucketName(),keyword,pageSize,continuationToken);
        }

        return null;

    }
}
