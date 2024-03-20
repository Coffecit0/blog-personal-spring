package com.coffeecito.controller;

import com.coffeecito.entity.UserEntity;
import com.coffeecito.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/record")
    public String recordPage() {
        return "/users/register";
    }

    @PostMapping("/register")
    public String register(UserEntity user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping(value = {"/login","/"})
    public String loginPage() {
        return "/users/login";
    }
}
