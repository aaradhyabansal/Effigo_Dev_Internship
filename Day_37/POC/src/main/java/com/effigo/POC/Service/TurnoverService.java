package com.effigo.POC.Service;

import com.effigo.POC.DTO.RegistrationDTO;
import com.effigo.POC.DTO.TurnoverDTO;
import com.effigo.POC.Mapper.TurnoverMapper;
import com.effigo.POC.Model.Registration;
import com.effigo.POC.Model.Turnover;
import com.effigo.POC.Repository.TurnoverRepo;
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
public class TurnoverService {
    private final TurnoverRepo turnoverRepo;
    private final TurnoverMapper turnoverMapper;
    private Long getCellValue(Cell cell) {
        if (cell == null) return 0L;
        if (cell.getCellType() == CellType.STRING) return Long.parseLong(cell.getStringCellValue());
        if (cell.getCellType() == CellType.NUMERIC) return Long.parseLong(String.valueOf((long) cell.getNumericCellValue()));
        return 0L;
    }
    private LocalDate parseDate(String dateStr, DateTimeFormatter formatter) {
        if (dateStr == null || dateStr.isEmpty()) return null;
        return LocalDate.parse(dateStr, formatter);
    }
    public void saveTurnOverFromExcel(MultipartFile file) {
        try(InputStream inputStream=file.getInputStream();
            Workbook workbook=new XSSFWorkbook(inputStream))
        {
            Sheet sheet=workbook.getSheetAt(0);
            Iterator<Row> rowIterator=sheet.iterator();
            List<Turnover> registrations=new ArrayList<>();
            boolean headersSkipped=false;
            while(rowIterator.hasNext())
            {
                Row row=rowIterator.next();
                if(!headersSkipped)
                {
                    headersSkipped=true;
                    continue;
                }
                TurnoverDTO dto = new TurnoverDTO();
                dto.setTurnoverYear1(getCellValue(row.getCell(0)));
                dto.setTurnoverYear2(getCellValue(row.getCell(1)));
                dto.setTurnoverYear3(getCellValue(row.getCell(2)));
                dto.setTurnoverYear4(getCellValue(row.getCell(3)));
                dto.setTurnoverYear5(getCellValue(row.getCell(4)));
                log.info("inside TurnoverService");
                registrations.add(turnoverMapper.toEntity(dto));
            }
            turnoverRepo.saveAll(registrations);


        }
        catch (Exception e) {
            throw new RuntimeException("Error processing Excel file: " + e.getMessage());
        }
    }
    public ResponseEntity<String> saveTurnover(TurnoverDTO dto) {
        try
        {
           turnoverRepo.save(turnoverMapper.toEntity(dto));
            return ResponseEntity.ok("Saved Turnover details");
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
