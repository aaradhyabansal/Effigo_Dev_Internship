package com.effigo.POC.Service;

import com.effigo.POC.DTO.FinancialDTO;
import com.effigo.POC.Mapper.FinancialMapper;
import com.effigo.POC.Repository.FinancialRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinancialService {
    private final FinancialRepo financialRepo;
    private final FinancialMapper financialMapper;

    public void saveDetails(FinancialDTO financialDTO) {
        log.info("Saving Financial Record1");
        financialRepo.save(financialMapper.toEntity(financialDTO));
    }

}
