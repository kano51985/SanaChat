package com.sana.support.controller;

import com.sana.common.domain.dto.MsgListDto;
import com.sana.common.domain.entity.SanaPersonalContacts;
import com.sana.common.domain.entity.SanaPrivateMsg;
import com.sana.common.response.R;
import com.sana.support.service.IPrivateMessagesService;
import com.sana.support.service.ISanaPersonalContacts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("message")
public class MessageController {
    private final IPrivateMessagesService privateMessagesService;

    private final ISanaPersonalContacts sanaPersonalContacts;

    public MessageController(IPrivateMessagesService privateMessagesService, ISanaPersonalContacts sanaPersonalContacts) {
        this.privateMessagesService = privateMessagesService;
        this.sanaPersonalContacts = sanaPersonalContacts;
    }

    /**
     * 获取用户消息列表
     */
    @GetMapping("list/{userId}")
    private R list(@PathVariable("userId")String userId) {
        List<SanaPersonalContacts> contactsList = sanaPersonalContacts.getTempContactByUserId(userId);
        return R.success(contactsList);
    }
    /**
     * 获取用户消息列表
     */
    @PostMapping("list/detail")
    private R detail(@RequestBody MsgListDto msgListDto) {
        System.out.println("msgListDto = " + msgListDto);
        List<SanaPrivateMsg> list = privateMessagesService.getChatDetails(msgListDto.getBelongToContact(),msgListDto.getReceiverId());
        System.out.println("list = " + list);
        return R.success(list);
    }
}
