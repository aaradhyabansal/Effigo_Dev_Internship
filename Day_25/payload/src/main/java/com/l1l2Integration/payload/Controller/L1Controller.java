package com.l1l2Integration.payload.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.l1l2Integration.payload.DTO.ExternalDto;
import com.l1l2Integration.payload.DTO.InternalDto;
import com.l1l2Integration.payload.DTO.ResultDto;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/payments")
@Slf4j
public class L1Controller {


    private static final String FILE_PATH = "src/main/resources/payload.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();
    private String l2Url = "http://localhost:8081/payments";



    private List<ExternalDto> readPayloadsFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();
            return objectMapper.readValue(file, new TypeReference<List<ExternalDto>>() {});
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }



    @PostMapping("/convert")
    public ResponseEntity<String> convertToInternal(@RequestBody List<ExternalDto> payloadExternalDTO) {
//        InternalDto paymentInternalDTO = payloadService.convertToInternal(payloadExternalDTO);
        List<ExternalDto> payloads = readPayloadsFromFile();
//        log.info("payloads {}",payloads);
//        payloads.addAll(payloadExternalDTO);
//        System.out.println(payloads);
//        writePayloadsToFile(payloads);
        String convertUrl=l2Url+"/convert";
        return restTemplate.postForEntity(convertUrl, payloadExternalDTO, String.class);
    }
    @GetMapping("/successful")
    public ResponseEntity<List<ResultDto>> successfulPayments() {
        String successUrl=l2Url+"/successful";
        ResponseEntity<List<ResultDto>> response = restTemplate.exchange(
                successUrl,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ResultDto>>() {}
        );

        return ResponseEntity.ok(response.getBody());
    }
    @GetMapping("/failed")
    public ResponseEntity<List<ResultDto>> unSuccessfulPayments() {

        String successUrl=l2Url+"/failed";
        ResponseEntity<List<ResultDto>> response = restTemplate.exchange(
                successUrl,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ResultDto>>() {}
        );
        log.info("Unsuccessful payments received:{}", response.getBody());
        return ResponseEntity.ok(response.getBody());
    }
    @DeleteMapping("/failedpayments/{id}")
    public ResponseEntity<String> deleteFRecord(@PathVariable long id)
    {
        String successUrl=l2Url+"/failedpayments"+id;
        ResponseEntity<String> response = restTemplate.exchange(
                successUrl,
                HttpMethod.DELETE,
                null,
                String.class
        );
        return ResponseEntity.ok(response.getBody());
    }
    @DeleteMapping("/successfulpayments/{id}")
    public ResponseEntity<String> deleteSRecord(@PathVariable long id)
    {
        String successUrl=l2Url+"/successfulpayments"+id;
        ResponseEntity<String> response = restTemplate.exchange(
                successUrl,
                HttpMethod.DELETE,
                null,
                String.class
        );
        return ResponseEntity.ok(response.getBody());
    }
}
