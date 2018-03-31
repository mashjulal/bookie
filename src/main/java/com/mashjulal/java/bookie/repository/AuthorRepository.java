package com.mashjulal.java.bookie.repository;

import com.mashjulal.java.bookie.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findByFirstNameContaining(String firstName);
    List<Author> findByLastNameContaining(String lastName);
    List<Author> findByFirstNameAndLastNameContaining(String firstName, String lastName);
}
