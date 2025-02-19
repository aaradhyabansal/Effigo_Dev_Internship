package com.mapper.practice.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "successful_payload_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessfulPayloadEntity {
    @Id
    private String id;

    private String paymentName;
    private String pay_id;
    private String pay_type;
    private String paymentReceiverName;
    private String amount;
    private String companyCode;
    private String transactionCode;
    private String plant;
    private Integer gst;
    private Integer status;

    @DBRef
    @JsonManagedReference
    private List<InvoiceEntity> invoices = new ArrayList<>();

    public void addInvoice(InvoiceEntity invoice) {
        invoices.add(invoice);
        invoice.setSuccessfulPayload(this);
    }

    public void removeInvoice(InvoiceEntity invoice) {
        invoices.remove(invoice);
        invoice.setSuccessfulPayload(null);
    }
}
