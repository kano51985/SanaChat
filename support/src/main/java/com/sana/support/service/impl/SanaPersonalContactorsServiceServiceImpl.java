package com.sana.support.service.impl;

import com.sana.common.domain.entity.SanaPersonalContacts;
import com.sana.support.repository.PersonalContactsRepository;
import com.sana.support.service.ISanaPersonalContactorsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanaPersonalContactorsServiceServiceImpl implements ISanaPersonalContactorsService {
    private final PersonalContactsRepository personalContactsRepository;


    public SanaPersonalContactorsServiceServiceImpl(PersonalContactsRepository personalContactsRepository) {
        this.personalContactsRepository = personalContactsRepository;
    }

    @Override
    public List<SanaPersonalContacts> getTempContactByUserId(String userId) {
        return personalContactsRepository.findAllByBelongToUser(userId);
    }
}
