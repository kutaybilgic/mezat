package com.group7.mezat.controllers;

import com.group7.mezat.services.UserService;
import com.group7.mezat.documents.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.group7.mezat.requests.UserPasswordUpdateRequest;
import com.group7.mezat.responses.UserResponse;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getOneUser(@PathVariable String id){
        return userService.getOneUser(id);
    }

    @GetMapping("userName/{userName}")
    public UserResponse getOneUserByName(@PathVariable String userName){
        return userService.getOneUserByName(userName);
    }

    @PostMapping
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody UserPasswordUpdateRequest newUser){
        return userService.updateUserPassword(id, newUser);
    }

    @PutMapping("/role/addToUser/{userName}")
    public User addCooperativeRoleToUser(@PathVariable String userName){
        return userService.addCooperativeRoleToUser(userName);
    }

}
