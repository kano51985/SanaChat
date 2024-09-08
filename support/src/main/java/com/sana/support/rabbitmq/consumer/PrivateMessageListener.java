package com.sana.support.rabbitmq.consumer;

import cn.hutool.core.util.ObjUtil;
import com.sana.common.domain.entity.SanaPrivateMsg;
import com.sana.support.repository.PrivateMessagesRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PrivateMessageListener {
    private final PrivateMessagesRepository privateMessagesRepository;

    public PrivateMessageListener(PrivateMessagesRepository privateMessagesRepository) {
        this.privateMessagesRepository = privateMessagesRepository;
    }

    @RabbitListener(queues = "private-queue")
    public void savePrivateMessage(SanaPrivateMsg message) {
        // 注意，我使用了最笨的方法，也就是用两个文档去解决双方的数据问题。实际上为了减少数据库消耗，需要对系统进行改进以实现一份document可同时供双方读取
        if (ObjUtil.isNotNull(message.getMessage())) {
            // 保存发送方的数据
            SanaPrivateMsg save = privateMessagesRepository.save(message);
            // 保存接收方的数据
            SanaPrivateMsg receiverMsg = new SanaPrivateMsg();
            receiverMsg.setMessage(message.getMessage());
            receiverMsg.setSenderId(message.getSenderId());
            receiverMsg.setReceiverAvatar(message.getSenderAvatar());
            receiverMsg.setReceiverId(message.getReceiverId());
            receiverMsg.setChatter(message.getSenderId());
            receiverMsg.setTimestamp(message.getTimestamp());
            receiverMsg.setBelongToContact(message.getReceiverId());
//            receiverMsg.setStatus(1);
            SanaPrivateMsg save1 = privateMessagesRepository.save(receiverMsg);
            System.out.println("发送者消息持久化成功！ ： " + save);
            System.out.println("接受者消息持久化成功！ ： " + save1);
        }

    }
}
