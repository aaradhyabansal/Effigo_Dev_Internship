package com.effigo.POC.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turnover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "turnover_year_1")
    private long turnoverYear1;

    @Column(name = "turnover_year_2")
    private long turnoverYear2;

    @Column(name = "turnover_year_3")
    private long turnoverYear3;

    @Column(name = "turnover_year_4")
    private long turnoverYear4;

    @Column(name = "turnover_year_5")
    private long turnoverYear5;
}
