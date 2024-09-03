package com.sana.support.service;

import com.sana.common.domain.dto.LoginDTO;
import com.sana.common.domain.dto.UserStateDTO;
import com.sana.common.domain.entity.SanaContacts;
import com.sana.common.domain.entity.SanaUser;

public interface ISanaUserService {
    SanaUser auth(LoginDTO loginDTO);
    boolean setUserState(UserStateDTO userStateDTO);

    SanaContacts getUserContacts(String id);
}
