package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.model.Author;

import java.util.List;

public interface AuthorService extends BaseService<Author, Long> {

    List<Author> findByFirstName(String firstName);
    List<Author> findByLastName(String lastName);
    List<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
