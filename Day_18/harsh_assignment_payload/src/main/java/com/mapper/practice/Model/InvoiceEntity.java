package com.mapper.practice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String invoice_type;
    private Date invoice_date;
    private Double invoice_amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payload_id")
    private PayloadEntity payload;

}
