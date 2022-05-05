package com.group7.mezat.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

}
