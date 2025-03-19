package com.effigo.POC.Controller;

import com.effigo.POC.DTO.FinancialDTO;
import com.effigo.POC.DTO.TurnoverDTO;
import com.effigo.POC.Service.TurnoverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/turnover")
@RequiredArgsConstructor
@Slf4j
public class TurnoverController {
    private final TurnoverService turnoverService;
    @PostMapping("/uploadturnover")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if(!file.getOriginalFilename().endsWith(".xlsx")) {
            return ResponseEntity.badRequest().body("Only .xlsx files are supported");
        }

        turnoverService.saveTurnOverFromExcel(file);
        return ResponseEntity.ok("File processed successfully!");
    }
    @PostMapping("/saveturnover")
    public ResponseEntity<String> saveTurnover(@RequestBody TurnoverDTO turnoverDTO) {
        try
        {
            log.info("save turnover1");
            turnoverService.saveTurnover(turnoverDTO);
            return ResponseEntity.ok("Saved Financial details");
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
