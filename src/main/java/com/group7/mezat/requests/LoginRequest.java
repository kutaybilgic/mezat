package com.group7.mezat.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String userMail;
    private String password;
}
