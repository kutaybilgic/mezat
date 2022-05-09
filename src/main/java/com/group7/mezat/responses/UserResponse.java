package com.group7.mezat.responses;

import com.group7.mezat.documents.Role;
import com.group7.mezat.documents.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserResponse {
    private String id;
    private String name;
    private String surname;
    private String userMail;
    private String address;
    private String phoneNum;
    private Collection<Role> roles = new ArrayList<>();

    public UserResponse(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.userMail = user.getUserMail();
        this.address = user.getAddress();
        this.phoneNum = user.getPhoneNum();
        this.roles = user.getRoles();
    }

}
