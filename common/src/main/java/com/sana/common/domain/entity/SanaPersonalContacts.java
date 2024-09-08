package com.sana.common.domain.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document("personal_contacts")
public class SanaPersonalContacts {
    @Id
    private ObjectId id;
    @Field("belong_to_user")
    private String belongToUser;
    @Field("list")
    private List<Lists> list;
    @Data
    private static class Lists {
        @Field("sender_avatar")
        private String senderAvatar;
        @Field("receiver_id")
        private String receiverId;
        @Field("receiver_avatar")
        private String receiverAvatar;
        @Field("receiver_nickname")
        private String receiverNickname;
        @Field("recent_message")
        private String recentMsg;
        @Field("send_time")
        private String sendTime;
    }
}
