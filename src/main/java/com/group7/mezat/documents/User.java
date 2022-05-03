package com.group7.mezat.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {

    @Id
    private String id;
    private String userName;
    private String password;
    private String userMail;
    private String address;
    private String phoneNum;
    private Role role;

    public User(String userName, String password, String userMail, String address, String phoneNum, Role role) {
        this.userName = userName;
        this.password = password;
        this.userMail = userMail;
        this.address = address;
        this.phoneNum = phoneNum;
        this.role = role;
    }
}
