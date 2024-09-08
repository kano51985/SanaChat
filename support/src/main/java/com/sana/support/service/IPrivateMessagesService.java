package com.sana.support.service;

import com.sana.common.domain.dto.MsgListDto;
import com.sana.common.domain.entity.SanaPrivateMsg;

import java.util.List;

public interface IPrivateMessagesService {
    List<SanaPrivateMsg> getChatDetails(String belongToContact,String receiverId);
}
