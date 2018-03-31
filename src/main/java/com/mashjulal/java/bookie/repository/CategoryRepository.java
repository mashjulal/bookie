package com.mashjulal.java.bookie.repository;

import com.mashjulal.java.bookie.model.Category;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
