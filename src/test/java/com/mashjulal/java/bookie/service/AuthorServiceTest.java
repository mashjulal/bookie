package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.BookieApplication;
import com.mashjulal.java.bookie.model.Author;
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
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookieApplication.class)
@TestPropertySource(locations = "classpath:./application.test.properties")
@Transactional
public class AuthorServiceTest extends TestCase {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AuthorService service;

    private List<Author> authors;


    @Before
    public void setup() {
        authors = DataSetInitializer.getAuthors();
    }

    @Test
    public void testFindById() {
        Author authorExpected = authors.get(0);
        Author authorActual = service.findById(authors.get(0).getId());

        assertEquals(authorExpected, authorActual);
        assertEquals(authorExpected.getBooks().size(), authorActual.getBooks().size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindById_NotFound() {
        service.findById(-1L);
    }

    @Test
    public void testCount() {
        long countExpected = authors.size();
        long countActual = service.count();

        assertEquals(countExpected, countActual);
    }

    @Test
    public void testExistsById_True() {
        boolean existsExpected = true;
        boolean existsActual = service.existsById(authors.get(0).getId());

        assertEquals(existsExpected, existsActual);
    }

    @Test
    public void testExistsById_False() {
        boolean existsExpected = false;
        boolean existsActual = service.existsById(-1L);

        assertEquals(existsExpected, existsActual);
    }

    @Test
    public void testFindAll() {
        List<Author> authorsExpected = authors;
        List<Author> authorsActual = (List<Author>) service.findAll();

        assertEquals(authorsExpected, authorsActual);
    }

    @Test
    public void testFindAllById() {
        List<Author> authorsExpected = authors.subList(0, 2);
        List<Author> authorsActual = (List<Author>) service.findAllById(
                authors.subList(0, 2).stream().map(Author::getId).collect(Collectors.toList()));

        assertEquals(authorsExpected, authorsActual);
    }

    @Test
    public void testDeleteById() {
        service.deleteById(authors.get(0).getId());
        authors.remove(0);

        List<Author> authorsExpected = authors;
        List<Author> authorsActual = (List<Author>) service.findAll();

        assertEquals(authorsExpected, authorsActual);
    }

    @Test
    public void testFindByFirstName() {
        List<Author> authorExpected = Arrays.asList(authors.get(0));
        List<Author> authorActual = service.findByFirstName(authors.get(0).getFirstName());

        assertEquals(authorExpected, authorActual);
    }

    @Test
    public void testFindByLastName() {
        List<Author> authorExpected = Arrays.asList(authors.get(0));
        List<Author> authorActual = service.findByLastName(authors.get(0).getLastName());

        assertEquals(authorExpected, authorActual);
    }

    @Test
    public void testFindByFirstNameAndLastName() {
        List<Author> authorExpected = Arrays.asList(authors.get(0));
        List<Author> authorActual = service.findByFirstNameAndLastName(authors.get(0).getFirstName(), authors.get(0).getLastName());

        assertEquals(authorExpected, authorActual);
    }
}
