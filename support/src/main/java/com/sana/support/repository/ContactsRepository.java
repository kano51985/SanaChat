package com.sana.support.repository;

import com.sana.common.domain.entity.SanaContacts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends MongoRepository<SanaContacts,String> {
    SanaContacts findByUserId(String userId);
}
