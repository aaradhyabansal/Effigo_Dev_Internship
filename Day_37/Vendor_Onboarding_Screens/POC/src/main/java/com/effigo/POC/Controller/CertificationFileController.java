package com.effigo.POC.Controller;

import com.effigo.POC.DTO.CertificationDTO;
import com.effigo.POC.DTO.RegistrationDTO;
import com.effigo.POC.DTO.S3ObjectDTO;
import com.effigo.POC.Service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/certification")
@RequiredArgsConstructor
@Slf4j
public class CertificationFileController {
    private final S3Service s3Service;
    private final ExcelToPdfService excelToPdfService;
    @Autowired
    private final DynamoDbClient dynamoDbClient;
    private final DynamoService dynamoService;
    private final SearchService searchService;
    private final CertificationService certificationService;



    @Value("${aws.s3.bucket-name}")
    String bucketName;

    @Value("${filesize}")
    long filesize;
    @Autowired
    private S3Client s3Client;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            long fileSizeInBytes = file.getSize();
            double fileSizeInMB = fileSizeInBytes / (1024.0 * 1024.0);
            log.info("fileSizeInMB: " + fileSizeInMB);
            if(fileSizeInMB > filesize) {
                throw new Exception("File size exceeds 50MB");
            }
            if(!file.getOriginalFilename().endsWith(".xlsx")) {
                return ResponseEntity.badRequest().body("Only .xlsx files are supported");
            }
            File uploadFile = excelToPdfService.convertExceltoPdf(file);
            String s3Url = s3Service.uploadCertificationFile(uploadFile, bucketName);
            long timestamp = Instant.now().toEpochMilli();
            String s3Key="effigo/certification/"+uploadFile.getName();
            log.info("s3Key: " + s3Key);

            certificationService.saveCertFromExcel(file,s3Key);
            s3Service.saveFileMetaData(dynamoDbClient,uploadFile.getName(),s3Key,bucketName,timestamp);

            log.info("File metadata saved successfully to DynamoDB.");
            return ResponseEntity.ok("File uploaded successfully! URL: " + s3Url);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFile(@RequestParam("keyword") String keyword,@RequestParam (value="pageSize",defaultValue = "10")int pageSize,@RequestParam (value="continuationToken",required = false)String continuationToken) {
        log.info("Searching keyword: "+ keyword);
        log.info("Searching pagesized: "+ pageSize);
        List<S3ObjectDTO> files=searchService.searchAcrossBucket(s3Client,keyword,pageSize,continuationToken);


        if(files.size()>0) {
            return ResponseEntity.ok(files);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No files found");
        }
    }
    @GetMapping("/search-download")
    public ResponseEntity<Resource> searchDownloadFile(@RequestParam("keyword") String keyword, @RequestParam (value="pageSize",defaultValue = "10")int pageSize, @RequestParam (value="continuationToken",required = false)String continuationToken) {
        log.info("D Searching keyword: "+ keyword);
        log.info("D Searching pagesized: "+ pageSize);
        return searchService.searchAcrossBucketAndDownload(s3Client,keyword,pageSize,continuationToken);
    }
    @PostMapping("/savecertification")
    public ResponseEntity<String> saveCertification(@RequestBody CertificationDTO certificationDTO) {
        try
        {
            certificationService.saveCertification(certificationDTO);
            return ResponseEntity.ok("Saved Certification details");
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
