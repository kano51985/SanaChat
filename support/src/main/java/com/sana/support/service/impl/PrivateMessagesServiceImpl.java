package com.sana.support.service.impl;

import com.sana.common.domain.entity.SanaPrivateMsg;
import com.sana.support.repository.PrivateMessagesRepository;
import com.sana.support.service.IPrivateMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class PrivateMessagesServiceImpl implements IPrivateMessagesService {
    private final PrivateMessagesRepository privateMessagesRepository;

    public PrivateMessagesServiceImpl(PrivateMessagesRepository privateMessagesRepository) {
        this.privateMessagesRepository = privateMessagesRepository;
    }

    @Override
    public List<SanaPrivateMsg> getChatDetails(String belongToContact,String receiverId) {
        return privateMessagesRepository.findByBelongToContactAndReceiverId(belongToContact,receiverId);
    }


}
