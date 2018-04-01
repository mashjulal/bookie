package com.mashjulal.java.bookie.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @RequestMapping("/{id}")
    public String getAuthor(@PathVariable("id") long id){
        return "Author with id " + id + "!";
    }
}
