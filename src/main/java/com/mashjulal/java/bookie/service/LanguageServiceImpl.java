package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.model.Book;
import com.mashjulal.java.bookie.model.Language;
import com.mashjulal.java.bookie.repository.BookRepository;
import com.mashjulal.java.bookie.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Language save(Language entity) {
        return repository.save(entity);
    }

    @Override
    public Iterable<Language> saveAll(Iterable<Language> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public Language findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Iterable<Language> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<Language> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(Long id) {
        List<Book> books = bookRepository.findByLanguageId(id);
        books.forEach(b -> b.setLanguage(null));
        bookRepository.saveAll(books);

        repository.deleteById(id);
    }

    @Override
    public void delete(Language entity) {
        repository.deleteById(entity.getId());
    }

    @Override
    public void deleteAll(Iterable<Language> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
