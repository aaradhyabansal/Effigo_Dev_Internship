package com.effigo.POC.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aws")
public class AwsProperties {

    private final S3 s3 = new S3();
    private final Credentials credentials = new Credentials();

    @Data
    public static class S3
    {
        private String bucketName;
        private String bucketDatalake;
        private String dynamoTablename;
    }
    @Data
    public static class Credentials {
        private String accessKey;
        private String secretKey;
    }
}
