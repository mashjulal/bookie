package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.model.Author;
import com.mashjulal.java.bookie.model.Book;
import com.mashjulal.java.bookie.model.Category;
import com.mashjulal.java.bookie.model.Language;

import java.util.List;
import java.util.Set;

public interface BookService extends BaseService<Book, Long> {

    List<Book> findByTitle(String title);
    List<Book> findByLanguage(Language language);
    List<Book> findByCategory(Category category);
    List<Book> findByAuthor(Author author);
}
