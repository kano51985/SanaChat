package com.sana.common.domain.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("users")
public class SanaUser {
    @Id
    private ObjectId id;
    @Field("user_id")
    private String userId;
    @Field("nickname")
    private String nickname;
    @Field("password")
    private String password;
    @Field("status")
    private Integer status;
    @Field("deleted")
    private Integer deleted;
    @Field("avatar")
    private String avatar;
}
