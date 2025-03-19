package com.effigo.POC.Controller;

import com.effigo.POC.DTO.ContactDetailsDTO;
import com.effigo.POC.Service.ContactDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
@Slf4j
public class ContactDetailsController {
    private final ContactDetailsService contactDetailsService;

    @PostMapping("/save")
    public ResponseEntity<String> saveContactDetails(@RequestBody ContactDetailsDTO contactDetailsDTO) {
        try {
            log.info("Saving Contact Details");
            contactDetailsService.saveDetails(contactDetailsDTO);
            return ResponseEntity.ok("Saved Contact Details");
        } catch (Exception e) {
            log.error("Error saving Contact Details: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
