package com.sana.support.service.impl;

import com.sana.common.domain.entity.SanaPersonalContacts;
import com.sana.support.repository.PersonalContactsRepository;
import com.sana.support.service.ISanaPersonalContacts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanaPersonalContactsServiceImpl implements ISanaPersonalContacts {
    private final PersonalContactsRepository personalContactsRepository;

    public SanaPersonalContactsServiceImpl(PersonalContactsRepository personalContactsRepository) {
        this.personalContactsRepository = personalContactsRepository;
    }

    @Override
    public List<SanaPersonalContacts> getTempContactByUserId(String userId) {
        return personalContactsRepository.findAllByBelongToUser(userId);
    }
}
