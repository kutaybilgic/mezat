package com.group7.mezat.security;

import com.group7.mezat.documents.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

    private String id;
    private String username;
    private String password;
    private final Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetails(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtUserDetails createUser(User user) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        user.getRoles().forEach(role -> authoritiesList.add(new SimpleGrantedAuthority(role.getName() )));
//        authoritiesList.add(new SimpleGrantedAuthority(user.getRoles().toString()));
        return new JwtUserDetails(user.getId(), user.getUserMail(), user.getPassword(), authoritiesList);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}