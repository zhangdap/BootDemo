package com.example.demo.service.impl;

import com.example.demo.dao.UserRepository;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByName(String username) {
        User user = userRepository.findByUsernameEquals(username);
        return user;
    }

    @Override
    public void saveUser(User user) {
        User u = userRepository.save(user);
        System.out.println(u);
    }
}
