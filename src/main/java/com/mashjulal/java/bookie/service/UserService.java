package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.model.Book;
import com.mashjulal.java.bookie.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService extends BaseService<User, Long> {

    List<User> findByLogin(String login);
    List<User> findByBook(Book book);
}
