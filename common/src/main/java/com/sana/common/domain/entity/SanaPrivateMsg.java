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
    @Field("sender_id")
    private String senderId;
    @Field("receiver_id")
    private String receiverId;
    @Field("message")
    private String message;
    @Field("timestamp")
    private Date timestamp;
    @Field("status")
    private Integer status;

    @Data
    public static class attachments {
        @Field("type")
        private String type;
        @Field("url")
        private List<String> url;
    }
}
