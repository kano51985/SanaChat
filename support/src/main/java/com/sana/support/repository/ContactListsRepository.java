package com.sana.support.repository;

import com.sana.common.domain.entity.SanaContactLists;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactListsRepository extends MongoRepository<SanaContactLists,String> {
}
