package com.group7.mezat.services;

import com.group7.mezat.documents.Role;
import com.group7.mezat.responses.UserResponse;
import com.group7.mezat.documents.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.group7.mezat.repos.UserRepository;
import com.group7.mezat.requests.UserPasswordUpdateRequest;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public UserResponse getOneUser(String id) {
        User user = userRepository.findById(id).orElse(null);
        return new UserResponse(user);
    }


    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


    public User updateUserPassword(String id, UserPasswordUpdateRequest newUser) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            User foundUser = user.get();
            foundUser.setPassword(newUser.getPassword());
            return userRepository.save(foundUser);
        }else{
            return null;
        }
    }

    public void saveUser(User user) {
        userRepository.insert(user);
    }

    public UserResponse getOneUserByEmail(String Email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserMail(Email));
        if (user.isPresent()){
            User found = user.get();
            return new UserResponse(found);
        }
        return null;
    }

    public User addCooperativeRoleToUser(String userName) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserMail(userName));
        if (user.isPresent()){
            User foundUser = user.get();
//            if (foundUser.getRole() == Role.ADMIN){
////! exception
//                return null;
//            }
//            foundUser.setRole(Role.COOPERATIVE);
            return userRepository.save(foundUser);
        }else{
            //! exception
            return null;
        }
    }
}
