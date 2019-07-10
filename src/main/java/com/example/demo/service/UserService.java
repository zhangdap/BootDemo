package com.example.demo.service;

import com.example.demo.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findUserByName(String username);

    void saveUser(User user);
}
