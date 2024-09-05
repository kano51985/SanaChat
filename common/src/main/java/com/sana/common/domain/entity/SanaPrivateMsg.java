package com.sana.common.domain.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@Document("private_messages")
public class SanaPrivateMsg {
    @Id
    private ObjectId id;
    /**
     * 属于哪个对话
     */
    @Field("belong_to_contact")
    private String belongToContact;
    @Field("sender_id")
    private String senderId;
    @Field("receiver_id")
    private String receiverId;
    @Field("receiver_avatar")
    private String receiverAvatar;
    @Field("message")
    private String message;
    @Field("timestamp")
    private String timestamp;
    @Field("status")
    private Integer status;
    @Field("operation")
    private Integer operation;

    @Data
    public static class attachments {
        @Field("type")
        private String type;
        @Field("url")
        private List<String> url;
    }
}
