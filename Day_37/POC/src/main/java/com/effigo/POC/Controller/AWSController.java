package com.effigo.POC.Controller;

import com.effigo.POC.AWS.S3BucketSizeChecker;
import com.effigo.POC.AWS.S3DataLakeMover;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

@RestController
@RequestMapping("/aws")
@RequiredArgsConstructor
@Slf4j
public class AWSController {
        private final S3BucketSizeChecker s3BucketSizeChecker;
        private final S3DataLakeMover s3DataLakeMover;
    @PostMapping("/move")
    public void moveToLake() {
        try(S3Client s3Client=S3Client.create())
        {
            log.info("Moving to s3 bucket");
            log.info("S3Client: " + s3Client);
            if(s3BucketSizeChecker.isBucketSizeExceeded(s3Client))
            {
                log.info("Bucket size exceeded");
                s3DataLakeMover.moveToDataLake(s3Client);
            }
            else
            {
                log.info("Bucket size not exceeded");
            }
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
        }
    }

}
