package com.effigo.POC.Controller;

import com.effigo.POC.DTO.FinancialDTO;
import com.effigo.POC.Service.FinancialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financial")
@RequiredArgsConstructor
@Slf4j
public class FinancialController {
    private final FinancialService financialService;
    @PostMapping("/savefinancial")
    public ResponseEntity<String> saveFinancial(@RequestBody FinancialDTO financialDTO) {
        try
        {
            log.info("Saving Financial Record");
            financialService.saveDetails(financialDTO);
            return ResponseEntity.ok("Saved Financial details");
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
