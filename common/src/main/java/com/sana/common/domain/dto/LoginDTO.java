package com.sana.common.domain.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class LoginDTO implements Serializable {
    private String id;
    private String password;
    private Integer rememberMe;
}
