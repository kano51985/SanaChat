package com.sana.support.repository;

import com.sana.common.domain.entity.SanaContactors;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactorsRepository extends MongoRepository<SanaContactors,String> {
    SanaContactors findByUserId(String userId);
}
