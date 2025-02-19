package com.mapper.practice.Repository;


import com.mapper.practice.Model.UnSuccessfulPayloadEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnSuccessfulPayloadRepository extends MongoRepository<UnSuccessfulPayloadEntity,String> {
}

