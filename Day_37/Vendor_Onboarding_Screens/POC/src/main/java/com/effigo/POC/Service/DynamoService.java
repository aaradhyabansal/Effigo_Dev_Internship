package com.effigo.POC.Service;

import com.effigo.POC.DTO.FileMetadataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.util.Map;

@Service
@Slf4j
public class DynamoService {

    public FileMetadataDTO searchFilesInDynamoDb(DynamoDbClient dynamoDbClient, String tableName, String keyword) {
        log.info("searchFilesInDynamoDb start");
        log.info("tableName: " + tableName);
        log.info("keyword: " + keyword);

        QueryRequest requestBuilder = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression("s3_key = :s3_key")
                .expressionAttributeValues(Map.of(":s3_key", AttributeValue.builder().s(keyword).build()))
                .limit(1)
                .build();


//        if(lastEvaluatedKey!=null) {
//            requestBuilder.exclusiveStartKey(lastEvaluatedKey);
//        }
        log.info("Request: {}", requestBuilder);

        QueryResponse response=dynamoDbClient.query(requestBuilder);
        log.info("response: {}", response);
        if(!response.items().isEmpty()) {
            Map<String, AttributeValue> result = response.items().get(0);
//            Map<String, AttributeValue> nextToken = response.lastEvaluatedKey();
//            log.info("Next Page Token: " + nextToken);
//            return result;
            log.info("found in DynamoDb");
            return new FileMetadataDTO(
                    result.get("s3_key").s(),
                    result.get("bucket_name").s(),
                    Long.parseLong(result.get("time_stamp").s())
            );
        }
        return null;
    }
}
