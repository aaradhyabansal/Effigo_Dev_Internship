package com.mapper.practice.Model;

import com.mapper.practice.DTO.InvoiceDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayloadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentName;
    private String pay_id;
    private String pay_type;
    private String paymentReceiverName;
    private String amount;
    private String companyCode;
    private String transactionCode;
    private String plant;
    private Integer gst;
    @OneToMany(mappedBy = "payload", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceEntity> invoices = new ArrayList<>();


    public void addInvoice(InvoiceEntity invoice) {
        invoices.add(invoice);
        invoice.setPayload(this);
    }

    public void removeInvoice(InvoiceEntity invoice) {
        invoices.remove(invoice);
        invoice.setPayload(null);
    }

}
