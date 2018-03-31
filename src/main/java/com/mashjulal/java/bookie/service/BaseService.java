package com.mashjulal.java.bookie.service;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.Optional;

public interface BaseService<T, ID extends Serializable> {
    T save(T entity);
    Iterable<T> saveAll(Iterable<T> entities);
    T findById(ID id);
    boolean existsById(ID id);
    Iterable<T> findAll();
    Iterable<T> findAllById(Iterable<ID> ids);
    long count();
    void deleteById(ID id);
    void delete(T entity);
    void deleteAll(Iterable<T> entities);
    void deleteAll();
}
