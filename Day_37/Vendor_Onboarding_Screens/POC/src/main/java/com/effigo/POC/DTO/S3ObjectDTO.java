package com.effigo.POC.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class S3ObjectDTO {
    private String key;
    private long size;
    private String bucketName;
    private String lastModified;

    private String storageClass;
}
