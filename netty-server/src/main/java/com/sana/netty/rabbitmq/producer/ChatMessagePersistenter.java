package com.sana.netty.rabbitmq.producer;

import cn.hutool.json.JSONUtil;
import com.sana.common.domain.entity.SanaPrivateMsg;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatMessagePersistenter {
    private final RabbitTemplate rabbitTemplate;
    @Autowired
    public ChatMessagePersistenter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void persistPrivateMessage(String sanaPrivateMsg) {
        SanaPrivateMsg msg = JSONUtil.toBean(sanaPrivateMsg, SanaPrivateMsg.class);
        rabbitTemplate.convertAndSend("chat-exchange","private.user." + msg.getSenderId(),msg);
    }
}
