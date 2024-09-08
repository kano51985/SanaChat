package com.sana.common.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MsgListDto implements Serializable {
    private String belongToContact;
    private String receiverId;
}
