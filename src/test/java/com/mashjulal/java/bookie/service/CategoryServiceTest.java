package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.BookieApplication;
import com.mashjulal.java.bookie.model.Category;
import com.mashjulal.java.bookie.utils.DataSetInitializer;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookieApplication.class)
@TestPropertySource(locations = "classpath:./application.test.properties")
@Transactional
public class CategoryServiceTest extends TestCase {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CategoryService service;

    private List<Category> categories;


    @Before
    public void setup() {
        categories = DataSetInitializer.getCategories();
    }

    @Test
    public void testFindById() {
        Category authorActual = service.findById(categories.get(0).getId());
        Category authorExpected = categories.get(0);

        assertEquals(authorExpected, authorActual);
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindById_NotFound() {
        service.findById(-1L);
    }

    @Test
    public void testCount() {
        long countActual = service.count();
        long countExpected = categories.size();

        assertEquals(countExpected, countActual);
    }

    @Test
    public void testExistsById_True() {
        boolean existsActual = service.existsById(categories.get(0).getId());
        boolean existsExpected = true;

        assertEquals(existsExpected, existsActual);
    }

    @Test
    public void testExistsById_False() {
        boolean existsActual = service.existsById(-1L);
        boolean existsExpected = false;

        assertEquals(existsExpected, existsActual);
    }

    @Test
    public void testFindAll() {
        List<Category> categoriesActual = (List<Category>) service.findAll();
        List<Category> categoriesExpected = categories;

        assertEquals(categoriesExpected, categoriesActual);
    }

    @Test
    public void testFindAllById() {
        List<Category> categoriesActual = (List<Category>) service.findAllById(
                categories.subList(0, 2).stream().map(Category::getId).collect(Collectors.toList()));
        List<Category> categoriesExpected = categories.subList(0, 2);

        assertEquals(categoriesExpected, categoriesActual);
    }

    @Test
    public void testDeleteById() {
        service.deleteById(categories.get(0).getId());
        categories.remove(0);

        List<Category> categoriesActual = categories;
        List<Category> categoriesExpected = (List<Category>) service.findAll();

        assertEquals(categoriesExpected, categoriesActual);
    }
}
