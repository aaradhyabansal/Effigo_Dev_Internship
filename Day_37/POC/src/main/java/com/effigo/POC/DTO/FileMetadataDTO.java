package com.effigo.POC.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileMetadataDTO {
    private String s3Key;
    private String bucketName;
    private Long timestamp;
}
