package com.sana.support.repository;

import com.sana.common.domain.entity.SanaPrivateMsg;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateMessagesRepository extends MongoRepository<SanaPrivateMsg,String> {
    List<SanaPrivateMsg> findByBelongToContactAndChatter(String userId,String chatter);
}
