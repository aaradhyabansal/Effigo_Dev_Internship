package com.mapper.practice.Repository;


import com.mapper.practice.Model.UnSuccessfulPayloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnSuccessfulPayloadRepository extends JpaRepository<UnSuccessfulPayloadEntity,Long> {
}

