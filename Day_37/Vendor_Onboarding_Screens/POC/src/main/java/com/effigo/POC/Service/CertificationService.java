package com.effigo.POC.Service;


import com.effigo.POC.DTO.CertificationDTO;
import com.effigo.POC.DTO.RegistrationDTO;
import com.effigo.POC.Mapper.CertificationMapper;
import com.effigo.POC.Model.Certification;
import com.effigo.POC.Repository.CertificationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificationService {
    private final CertificationRepo certificationRepo;
    private final CertificationMapper certificationMapper;

    private String getCellValue(Cell cell)
    {
        if(cell == null) return "";
        if(cell.getCellType()== CellType.STRING) return cell.getStringCellValue();
        log.info("hi from getCellValue:"+(long)cell.getNumericCellValue());
        if(cell.getCellType()== CellType.NUMERIC) return String.valueOf((long)cell.getNumericCellValue());
        return "";
    }
    private LocalDate parseDate(String dateStr, DateTimeFormatter formatter)
    {
        if (dateStr == null || dateStr.isEmpty()) return null;
        return LocalDate.parse(dateStr, formatter);
    }
    public void saveCertFromExcel(MultipartFile file,String s3Key) {
        try(InputStream inputStream=file.getInputStream();
        Workbook workbook=new XSSFWorkbook(inputStream))
        {
            Sheet sheet=workbook.getSheetAt(0);
            Iterator<Row>rowIterator=sheet.iterator();

            List<Certification>certifications=new ArrayList<>();
            boolean headerSkipped=false;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            while(rowIterator.hasNext())
            {
                Row row=rowIterator.next();

                if(!headerSkipped)
                {
                    headerSkipped=true;
                    continue;
                }
                CertificationDTO certificationDto=new CertificationDTO();
                certificationDto.setCertifiedBy(getCellValue(row.getCell(0)));
                log.info("certificationDto: " + certificationDto);
                log.info("certificationDto111: " + (getCellValue(row.getCell(1))));
                certificationDto.setCertificationNo(Long.parseLong(getCellValue(row.getCell(1))));

                certificationDto.setIssuer(getCellValue(row.getCell(2)));
                certificationDto.setPlaceOfCert(getCellValue(row.getCell(3)));
                log.info("inside certificationService");
                certificationDto.setEffectiveDate(parseDate(getCellValue(row.getCell(4)), formatter));
                certificationDto.setExpiryDate(parseDate(getCellValue(row.getCell(5)), formatter));
                certificationDto.setS3Key(s3Key);
                certifications.add(certificationMapper.toEntity(certificationDto));
            }
            certificationRepo.saveAll(certifications);

        }
        catch(Exception e)
        {
            throw new RuntimeException("Error processing Excel file: " + e.getMessage());
        }
    }
    public ResponseEntity<String> saveCertification(CertificationDTO certificationDTO) {
        try
        {
            certificationRepo.save(certificationMapper.toEntity(certificationDTO));
            return ResponseEntity.ok("Saved Certification details");
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
