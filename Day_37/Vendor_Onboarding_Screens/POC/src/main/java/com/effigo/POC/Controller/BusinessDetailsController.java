package com.effigo.POC.Controller;

import com.effigo.POC.DTO.BusinessDetailsDTO;

import com.effigo.POC.Service.BusinessDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/businessdetails")
@RequiredArgsConstructor
@Slf4j
public class BusinessDetailsController {

    private final BusinessDetailsService businessDetailsService;
    @PostMapping("/save")
    public ResponseEntity<String> saveTurnover(@RequestBody BusinessDetailsDTO businessDetailsDTO) {
        try
        {
            log.info("save bd1");
           businessDetailsService.saveDetails(businessDetailsDTO);
            return ResponseEntity.ok("Saved Business details");
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
