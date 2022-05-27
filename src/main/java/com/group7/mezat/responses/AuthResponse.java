package com.group7.mezat.responses;

import com.group7.mezat.documents.Role;
import lombok.Data;

import java.util.Collection;

@Data
public class AuthResponse {

    String message;
    String  userId;
    Collection<Role> roles;
}