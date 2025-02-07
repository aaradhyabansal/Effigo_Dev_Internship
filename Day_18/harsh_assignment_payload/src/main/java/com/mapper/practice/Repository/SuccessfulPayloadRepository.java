package com.mapper.practice.Repository;

import com.mapper.practice.Model.SuccessfulPayloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessfulPayloadRepository extends JpaRepository<SuccessfulPayloadEntity,Long> {
}
