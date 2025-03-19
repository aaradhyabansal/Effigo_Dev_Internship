package com.effigo.POC.Service;

import com.effigo.POC.DTO.ContactDetailsDTO;
import com.effigo.POC.Mapper.ContactDetailsMapper;
import com.effigo.POC.Repository.ContactDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactDetailsService {
    private final ContactDetailsRepo contactDetailsRepo;
    private final ContactDetailsMapper contactDetailsMapper;

    public ResponseEntity<String> saveDetails(ContactDetailsDTO dto) {
        try {
            contactDetailsRepo.save(contactDetailsMapper.toEntity(dto));
            return ResponseEntity.ok("Saved Contact Details");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
