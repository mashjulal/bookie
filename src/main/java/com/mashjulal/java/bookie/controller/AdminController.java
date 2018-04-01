package com.mashjulal.java.bookie.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("")
    public String getIndex(){
        return "Admin page";
    }

    @RequestMapping("/login")
    public String login(){
        return "Admin login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "Admin logout";
    }
}
