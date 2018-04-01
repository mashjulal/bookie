package com.mashjulal.java.bookie.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @RequestMapping("")
    public String getBooks(){
        return "That's all books that Bookie has!";
    }

    @RequestMapping("/{id}")
    public String getBook(@PathVariable("id") long id){
        return "Book with id " + id + "!";
    }
}