package com.group7.mezat.repos;

import com.group7.mezat.documents.User;
import com.group7.mezat.responses.UserResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserMail(String userMail);

}
