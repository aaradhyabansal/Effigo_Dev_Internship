package com.effigo.POC.Service;

import com.effigo.POC.DTO.BusinessDetailsDTO;
import com.effigo.POC.Mapper.BusinessDetailsMapper;
import com.effigo.POC.Repository.BusinessDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessDetailsService {
    private final BusinessDetailsRepo businessDetailsRepo;
    private final BusinessDetailsMapper businessDetailsMapper;
    public ResponseEntity<String> saveDetails(BusinessDetailsDTO dto) {
        try
        {
            businessDetailsRepo.save(businessDetailsMapper.toEntity(dto));
            return ResponseEntity.ok("Saved Business Details");
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
