package com.sana.common.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthDTO implements Serializable {
    private String token;
    private String userId;
}
