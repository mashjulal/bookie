package com.mashjulal.java.bookie.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @RequestMapping("/")
    public String getIndex(){
        return "Hello World From Bookie!";
    }

    @RequestMapping("/about")
    public String getAbout(){
        return "About page content";
    }
}
