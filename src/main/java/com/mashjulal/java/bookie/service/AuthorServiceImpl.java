package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.model.Book;
import com.mashjulal.java.bookie.model.User;
import com.mashjulal.java.bookie.repository.AuthorRepository;
import com.mashjulal.java.bookie.model.Author;
import com.mashjulal.java.bookie.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Author save(Author entity) {
        return authorRepository.save(entity);
    }

    @Override
    public Iterable<Author> saveAll(Iterable<Author> entities) {
        return authorRepository.saveAll(entities);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Iterable<Author> findAllById(Iterable<Long> ids) {
        return authorRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return authorRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        List<Book> books = bookRepository.findByAuthorId(id);
        books.forEach(b -> b.setAuthors(new ArrayList<>()));
        bookRepository.saveAll(books);

        authorRepository.deleteById(id);
    }

    @Override
    public void delete(Author entity) {
        authorRepository.deleteById(entity.getId());
    }

    @Override
    public void deleteAll(Iterable<Author> entities) {
        authorRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();
    }

    @Override
    public List<Author> findByFirstName(String firstName) {
        return authorRepository.findByFirstNameContaining(firstName);
    }

    @Override
    public List<Author> findByLastName(String lastName) {
        return authorRepository.findByLastNameContaining(lastName);
    }

    @Override
    public List<Author> findByFirstNameAndLastName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastNameContaining(firstName, lastName);
    }
}
