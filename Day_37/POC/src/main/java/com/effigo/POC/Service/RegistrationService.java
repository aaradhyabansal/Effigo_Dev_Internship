package com.effigo.POC.Service;

import com.effigo.POC.DTO.RegistrationDTO;
import com.effigo.POC.Mapper.RegistrationMapper;
import com.effigo.POC.Model.Registration;
import com.effigo.POC.Repository.RegistrationRepo;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    private final RegistrationRepo registrationRepo;
    private final RegistrationMapper registrationMapper;

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue();
        if (cell.getCellType() == CellType.NUMERIC) return String.valueOf((long) cell.getNumericCellValue());
        return "";
    }
    private LocalDate parseDate(String dateStr, DateTimeFormatter formatter) {
        if (dateStr == null || dateStr.isEmpty()) return null;
        return LocalDate.parse(dateStr, formatter);
    }
    public void saveRegFromExcel(MultipartFile file,String s3Key) {
        try(InputStream inputStream=file.getInputStream();
            Workbook workbook=new XSSFWorkbook(inputStream))
        {
            Sheet sheet=workbook.getSheetAt(0);
            Iterator<Row> rowIterator=sheet.iterator();
            List<Registration> registrations=new ArrayList<>();
            boolean headersSkipped=false;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            while(rowIterator.hasNext())
            {
                Row row=rowIterator.next();
                if(!headersSkipped)
                {
                    headersSkipped=true;
                    continue;
                }
                RegistrationDTO dto = new RegistrationDTO();
                dto.setRegisteredWith(getCellValue(row.getCell(0)));
                dto.setClassOfRegistration(getCellValue(row.getCell(1)));
                dto.setPlaceOfRegistration(getCellValue(row.getCell(2)));
                dto.setServiceRegTo(getCellValue(row.getCell(3)));
                dto.setEffectiveDate(parseDate(getCellValue(row.getCell(4)), formatter));
                dto.setExpiryDate(parseDate(getCellValue(row.getCell(5)), formatter));
                dto.setS3Key(s3Key);
                log.info("inside RegistrationService");
                registrations.add(registrationMapper.toEntity(dto));
            }
            registrationRepo.saveAll(registrations);


        }
        catch (Exception e) {
            throw new RuntimeException("Error processing Excel file: " + e.getMessage());
        }
    }

    public ResponseEntity<String> saveRegistration(RegistrationDTO registrationDTO) {
        try
        {
            registrationRepo.save(registrationMapper.toEntity(registrationDTO));
            return ResponseEntity.ok("Saved Registration details");
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
