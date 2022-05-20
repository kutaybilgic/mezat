package com.group7.mezat.services;

import com.group7.mezat.documents.User;
import com.group7.mezat.repos.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.group7.mezat.security.JwtUserDetails;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepo;

    public UserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByUserMail(email);
        return JwtUserDetails.createUser(user);
    }


    public UserDetails loadUserById(String id) {
        User user = userRepo.findById(id).get();
        return JwtUserDetails.createUser(user);
    }

}