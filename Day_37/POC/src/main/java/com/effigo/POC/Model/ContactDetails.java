package com.effigo.POC.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "level1_name")),
            @AttributeOverride(name = "designation", column = @Column(name = "level1_designation")),
            @AttributeOverride(name = "contactNumber", column = @Column(name = "level1_contact_number")),
            @AttributeOverride(name = "emailId", column = @Column(name = "level1_email_id"))
    })
    private Contact level1;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "level2_name")),
            @AttributeOverride(name = "designation", column = @Column(name = "level2_designation")),
            @AttributeOverride(name = "contactNumber", column = @Column(name = "level2_contact_number")),
            @AttributeOverride(name = "emailId", column = @Column(name = "level2_email_id"))
    })
    private Contact level2;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "level3_name")),
            @AttributeOverride(name = "designation", column = @Column(name = "level3_designation")),
            @AttributeOverride(name = "contactNumber", column = @Column(name = "level3_contact_number")),
            @AttributeOverride(name = "emailId", column = @Column(name = "level3_email_id"))
    })
    private Contact level3;
}
