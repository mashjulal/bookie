package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.model.Book;
import com.mashjulal.java.bookie.model.Category;
import com.mashjulal.java.bookie.repository.BookRepository;
import com.mashjulal.java.bookie.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Category save(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public Iterable<Category> saveAll(Iterable<Category> entities) {
        return categoryRepository.saveAll(entities);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Long> ids) {
        return categoryRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        List<Book> books = bookRepository.findByCategoryId(id);
        books.forEach(b -> b.setCategories(new ArrayList<>()));
        bookRepository.saveAll(books);

        categoryRepository.deleteById(id);
    }

    @Override
    public void delete(Category entity) {
        categoryRepository.deleteById(entity.getId());
    }

    @Override
    public void deleteAll(Iterable<Category> entities) {
        categoryRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}
