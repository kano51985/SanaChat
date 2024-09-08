package com.sana.support.service;

import com.sana.common.domain.entity.SanaPersonalContacts;

import java.util.List;

public interface ISanaPersonalContactorsService {
    List<SanaPersonalContacts> getTempContactByUserId(String userId);
}
