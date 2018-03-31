package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.model.*;
import com.mashjulal.java.bookie.repository.BookRepository;
import com.mashjulal.java.bookie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    @Override
    public List<Book> findByLanguage(Language language) {
        return bookRepository.findByLanguageId(language.getId());
    }

    @Override
    public List<Book> findByCategory(Category category) {
        return bookRepository.findByCategoryId(category.getId());
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        return bookRepository.findByAuthorId(author.getId());
    }

    @Override
    public Book save(Book entity) {
        return bookRepository.save(entity);
    }

    @Override
    public Iterable<Book> saveAll(Iterable<Book> entities) {
        return bookRepository.saveAll(entities);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Iterable<Book> findAllById(Iterable<Long> ids) {
        return bookRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public void deleteById(final Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent())
            return;
        final Book book = optionalBook.get();
        book.setAuthors(new ArrayList<>());
        book.setCategories(new ArrayList<>());
        bookRepository.save(book);

        List<User> users = userRepository.findByBookId(book.getId());
        users.forEach(u -> u.getBooks().removeIf(b -> book.getId() == id));
        userRepository.saveAll(users);

        bookRepository.deleteById(id);
    }

    @Override
    public void delete(Book entity) {
        bookRepository.deleteById(entity.getId());
    }

    @Override
    public void deleteAll(Iterable<Book> entities) {
        bookRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }


}
