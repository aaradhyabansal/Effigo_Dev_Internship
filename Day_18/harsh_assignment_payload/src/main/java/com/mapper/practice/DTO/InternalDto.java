package com.mapper.practice.DTO;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InternalDto {
    private Long id;
    private String name;
    private String email;
}
