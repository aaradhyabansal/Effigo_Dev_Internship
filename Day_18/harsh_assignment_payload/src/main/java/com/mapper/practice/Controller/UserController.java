package com.mapper.practice.Controller;

import com.mapper.practice.DTO.ExternalDto;
import com.mapper.practice.DTO.InternalDto;
import com.mapper.practice.Model.SuccessfulPayloadEntity;
import com.mapper.practice.Model.UnSuccessfulPayloadEntity;
import com.mapper.practice.Service.PayloadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/payments")
public class UserController {
    private final PayloadService payloadService;

    public UserController(PayloadService payloadService) {
        this.payloadService = payloadService;
    }

    @PostMapping("/convert")
    public ResponseEntity<InternalDto> convertToInternal(@RequestBody ExternalDto payloadExternalDTO) {
        InternalDto paymentInternalDTO = payloadService.convertToInternal(payloadExternalDTO);
        return ResponseEntity.ok(paymentInternalDTO);
    }
    @GetMapping("/successful")
    public List<SuccessfulPayloadEntity> successfulPayments() {

        return payloadService.getSuccessfulPayments();
    }
    @GetMapping("/failed")
    public List<UnSuccessfulPayloadEntity> unSuccessfulPayments() {
        return payloadService.getUnSuccessfulPayments();
    }
}
