package com.mapper.practice.Repository;

import com.mapper.practice.Model.PayloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayloadRepository extends JpaRepository<PayloadEntity,Long> {
}
