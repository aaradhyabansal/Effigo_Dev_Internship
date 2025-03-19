package com.effigo.POC.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelToPdfService {
    public File convertExceltoPdf(MultipartFile file) throws Exception {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        String originalFilename = file.getOriginalFilename();
        log.info("OriginalFilename: " + originalFilename);
        String fileNameWithoutExtension = originalFilename != null ? originalFilename.replaceFirst("[.][^.]+$", "") : "output";
        log.info("fileNameWithoutExtension: " + fileNameWithoutExtension);
//        Sheet sheet = workbook.getSheetAt(0);
//        String pdfPath="output.pdf";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc=new PdfDocument(writer);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString=date.format(formatter);
        Document doc=new Document(pdfDoc);

       for(Sheet sheet:workbook)
       {
            for(Row row:sheet)
            {
                StringBuilder rowText = new StringBuilder();
                for(Cell cell:row)
                {
                    rowText.append(cell.toString()).append(" | ");
                }
                doc.add(new Paragraph(rowText.toString()));
            }
       }

       doc.close();
       workbook.close();
       dateString=dateString+"_"+fileNameWithoutExtension;
       File pdfFile=new File(dateString+".pdf");
//       File pdfFile=File.createTempFile(dateString, ".pdf");
       try(FileOutputStream fos=new FileOutputStream(pdfFile))
       {
           fos.write(out.toByteArray());
       }
       log.info("File name:"+pdfFile.getName());
        return pdfFile;


    }
}
