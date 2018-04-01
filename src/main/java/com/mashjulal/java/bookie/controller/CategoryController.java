package com.mashjulal.java.bookie.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @RequestMapping("/{id}")
    public String getCategory(@PathVariable("id") long id){
        return "Category with id " + id + "!";
    }
}
