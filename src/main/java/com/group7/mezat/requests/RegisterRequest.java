package com.group7.mezat.requests;

import lombok.Data;

@Data
public class RegisterRequest {

    String userMail;
    String name;
    String surname;
    String address;
    String phoneNum;
    String password;
    String passwordAgain;

}