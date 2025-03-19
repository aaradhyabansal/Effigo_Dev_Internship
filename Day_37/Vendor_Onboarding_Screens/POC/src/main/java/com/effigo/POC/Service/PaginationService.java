package com.effigo.POC.Service;

import com.effigo.POC.DTO.S3ObjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaginationService {
    private final S3Service s3Service;
    public List<S3ObjectDTO> searchFilesinBucket(S3Client s3Client, String bucketName, String prefix, int pageSize, String continuationToken) {
        ListObjectsV2Request.Builder requestBuilder = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .prefix(prefix)
                .maxKeys(pageSize);

        if(continuationToken!=null)
        {
            requestBuilder.continuationToken(continuationToken);
        }
        ListObjectsV2Response response=s3Client.listObjectsV2(requestBuilder.build());
//        List<S3Object> files=response.contents();
        String nextToken=response.nextContinuationToken();
        System.out.println("Next Page Token: " + nextToken);
//        return files;
        return response.contents().stream()
                .filter(obj->obj.key().contains(prefix))
                .map((obj)->new S3ObjectDTO(
                        obj.key(),
                        obj.size(),
                        bucketName,
                        obj.lastModified().toString(),
                        obj.storageClassAsString()
                ))
                .collect(Collectors.toList());
    }
    public ResponseEntity<Resource> searchFilesinBucketAndDownload(S3Client s3Client, String bucketName, String prefix, int pageSize, String continuationToken) {
        ListObjectsV2Request.Builder requestBuilder = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .prefix(prefix)
                .maxKeys(pageSize);

        if(continuationToken!=null)
        {
            requestBuilder.continuationToken(continuationToken);
        }
        ListObjectsV2Response response=s3Client.listObjectsV2(requestBuilder.build());
//        List<S3Object> files=response.contents();
        String nextToken=response.nextContinuationToken();
        System.out.println("Next Page Token: " + nextToken);
//        return files;
        List<S3Object> matchingFiles=response.contents()
                .stream()
                .collect(Collectors.toList());
        if (matchingFiles.isEmpty()) {
            return null;
        }
        String fileKey = matchingFiles.get(0).key();
        System.out.println("File Key: " + fileKey);
        return s3Service.downloadFile(bucketName, fileKey);
    }
}