package com.sana.support.service;

import com.sana.common.domain.dto.LoginDTO;
import com.sana.common.domain.dto.UserStateDTO;
import com.sana.common.domain.entity.SanaContacts;
import com.sana.common.domain.entity.SanaUser;
import com.sana.common.domain.vo.BasicUserInfoVO;

public interface ISanaUserService {
    String auth(LoginDTO loginDTO);
    boolean setUserState(UserStateDTO userStateDTO);

    SanaContacts getUserContacts(String id);

    BasicUserInfoVO getUserBasicInfo(String id);
}
