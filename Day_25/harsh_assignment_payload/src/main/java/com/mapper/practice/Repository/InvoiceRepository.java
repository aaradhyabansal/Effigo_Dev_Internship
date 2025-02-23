package com.mapper.practice.Repository;

import com.mapper.practice.Model.InvoiceEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Long> {

}
