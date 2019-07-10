package com.example.demo.controller;

import com.example.demo.dao.UserRepository;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordHelper passwordHelper;

    @Autowired
    private UserRepository userRepository;

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("login")
    public Object login() {
        return "Here is Login page";
    }

    @GetMapping("unauthc")
    public Object unauthc() {
        return "Here is Unauthc page";
    }

    @GetMapping("doLogin")
    public Object doLogin(@RequestParam String username, @RequestParam String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            return "password error!";
        } catch (UnknownAccountException uae) {
            return "username error!";
        }

        User user = userService.findUserByName(username);
        subject.getSession().setAttribute("user", user);
        return "SUCCESS";
    }

    @PostMapping("register")
    public Object register(@RequestBody User user) {
        passwordHelper.encryptPassword(user);
        userService.saveUser(user);
        return "SUCCESS";
    }

    @GetMapping(value = "/test")
    public Object test(@RequestParam(required = false, defaultValue = "zhangyong") String name) {

        logger.info(name);
        return userRepository.getabs(name);
    }
}