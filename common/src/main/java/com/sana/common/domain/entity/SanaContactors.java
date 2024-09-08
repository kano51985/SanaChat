package com.sana.common.domain.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@Document("contactors")
public class SanaContactors {
    @Id
    private ObjectId id;

    @Field("user_id")
    private String userId;

    private List<Group> contacts;

    @Data
    public static class Group {
        @Field("group_name")
        private String groupName;

        @Field("group_members")
        private List<Contact> groupMembers;

        // 添加联系人
        public void addContact(Contact contact) {
            this.groupMembers.add(contact);
        }

        // 删除联系人
        public void removeContact(Contact contact) {
            this.groupMembers.remove(contact);
        }
    }

    @Data
    public static class Contact {
        @Field("added_at")
        private Date addedAt;

        private String nickname;
        private String remark;
        
        @Field("user_id")
        private String userId;

        private String avatar;
        private String signature;
    }
}
