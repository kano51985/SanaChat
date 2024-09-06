package com.sana.common.domain.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("contact_lists")
public class SanaContactLists {
    @Id
    private ObjectId id;
    @Field("personal_contacts")
    private String personalContacts;
    @Field("group_contacts")
    private String groupContacts;
}
