package com.group7.mezat.responses;

import com.group7.mezat.documents.Role;
import com.group7.mezat.documents.User;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String userName;
    private String userMail;
    private String address;
    private String phoneNum;
    private Role role;

    public UserResponse(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userMail = user.getUserMail();
        this.address = user.getAddress();
        this.phoneNum = user.getPhoneNum();
        this.role = user.getRole();
    }

}
