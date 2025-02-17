package com.mapper.practice.CustomDataType;

import com.mapper.practice.DTO.InternalDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ValidationState {

    private boolean failed;
    private String reason;
    private int statusVal;
    private InternalDto internalDto;
}
