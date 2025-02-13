package com.mapper.practice.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnSuccessfulPayloadEntity {
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
    private Integer status;
    private String reason_failure;
    @OneToMany(mappedBy = "unSuccessfulPayload", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<InvoiceEntity> invoices = new ArrayList<>();


    public void addInvoice(InvoiceEntity invoice) {
        invoices.add(invoice);
        invoice.setUnSuccessfulPayload(this);
    }

    public void removeInvoice(InvoiceEntity invoice) {
        invoices.remove(invoice);
        invoice.setUnSuccessfulPayload(null);
    }

}
