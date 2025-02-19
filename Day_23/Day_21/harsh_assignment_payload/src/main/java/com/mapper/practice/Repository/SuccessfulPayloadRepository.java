package com.mapper.practice.Repository;

import com.mapper.practice.Model.SuccessfulPayloadEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessfulPayloadRepository extends MongoRepository<SuccessfulPayloadEntity,String> {
}
