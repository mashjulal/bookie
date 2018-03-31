package com.mashjulal.java.bookie.repository;

import com.mashjulal.java.bookie.model.Book;
import com.mashjulal.java.bookie.model.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByTitleContaining(@Param("title") String title);
    List<Book> findByLanguageId(@Param("language_id") Long id);
    @Query("select b from Book as b inner join b.categories as c with c.id = :category_id")
    List<Book> findByCategoryId(@Param("category_id") Long id);
    @Query("select b from Book as b inner join b.authors as a with a.id = :author_id")
    List<Book> findByAuthorId(@Param("author_id") Long id);
}
