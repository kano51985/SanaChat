package com.sana.support.repository;

import com.sana.common.domain.entity.SanaPersonalContacts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalContactsRepository extends MongoRepository<SanaPersonalContacts,String> {
    List<SanaPersonalContacts> findAllByBelongToUser(String userId);
}
