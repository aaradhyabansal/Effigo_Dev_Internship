package com.mapper.practice.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "invoice_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceEntity {
   @Id
    private String id;
    private String invoice_type;
    private LocalDate invoice_date;
    private Double invoice_amount;
    @DBRef
    @JsonBackReference
    private SuccessfulPayloadEntity successfulPayload;

   @DBRef
    @JsonBackReference
    private UnSuccessfulPayloadEntity unSuccessfulPayload;


}
