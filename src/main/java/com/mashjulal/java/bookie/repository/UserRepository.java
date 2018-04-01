package com.mashjulal.java.bookie.repository;

import com.mashjulal.java.bookie.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLoginContaining(@Param("login") String login);
    @Query("select u from User as u inner join u.books as b with b.id = :book_id")
    List<User> findByBookId(@Param("book_id") Long id);
}
