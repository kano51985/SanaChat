package com.sana.support.repository;

import com.sana.common.domain.entity.SanaUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanaUserRepository extends MongoRepository<SanaUser,String> {
    SanaUser findSanaUserByUserIdAndAndPassword(String userId,String password);
    SanaUser findByUserId(String userId);
}
