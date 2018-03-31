package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.model.Book;
import com.mashjulal.java.bookie.model.User;
import com.mashjulal.java.bookie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public Iterable<User> saveAll(Iterable<User> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(User entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<User> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<User> findByLogin(String login) {
        return repository.findByLoginContaining(login);
    }

    @Override
    public List<User> findByBook(Book book) {
        return repository.findByBookId(book.getId());
    }
}
