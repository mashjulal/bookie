package com.mashjulal.java.bookie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/sign_up")
    public String signUp() {
        return "";
    }

    @RequestMapping("/sign_in")
    public String signIn() {
        return "";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "";
    }
}
