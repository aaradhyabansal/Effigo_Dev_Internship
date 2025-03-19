package com.effigo.POC.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class S3ObjectDTO {
    private String key;
    private long size;
    private String bucketName;
    private String lastModified;

    private String storageClass;
}
