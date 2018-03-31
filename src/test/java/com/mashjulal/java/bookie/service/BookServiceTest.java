package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.BookieApplication;
import com.mashjulal.java.bookie.model.Author;
import com.mashjulal.java.bookie.model.Book;
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
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookieApplication.class)
@TestPropertySource(locations = "classpath:./application.test.properties")
@Transactional
public class BookServiceTest extends TestCase {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookService service;

    private List<Book> books;


    @Before
    public void setup() {
        books = DataSetInitializer.getBooks();
    }

    @Test
    public void testFindById() {
        Book bookActual = service.findById(books.get(0).getId());
        Book bookExpected = books.get(0);

        assertEquals(bookExpected.getId(), bookActual.getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindById_NotFound() {
        service.findById(-1L);
    }

    @Test
    public void testCount() {
        long countActual = service.count();
        long countExpected = books.size();

        assertEquals(countExpected, countActual);
    }

    @Test
    public void testExistsById_True() {
        boolean existsActual = service.existsById(books.get(0).getId());
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
        List<Book> booksActual = (List<Book>) service.findAll();
        List<Book> booksExpected = books;

        assertEquals(booksExpected, booksActual);
    }

    @Test
    public void testFindAllById() {
        List<Book> booksActual = (List<Book>) service.findAllById(
                books.subList(0, 2).stream().map(Book::getId).collect(Collectors.toList()));
        List<Book> booksExpected = books.subList(0, 2);

        assertEquals(booksExpected, booksActual);
    }

    @Test
    public void testDeleteById() {
        service.deleteById(books.get(0).getId());
        books.remove(0);

        List<Book> booksExpected = books;
        List<Book> booksActual = (List<Book>) service.findAll();

        assertEquals(booksExpected, booksActual);
    }

    @Test
    public void testFindByTitle() {
        List<Book> bookActual = service.findByTitle(books.get(0).getTitle());
        List<Book> bookExpected = Arrays.asList(books.get(0));

        assertEquals(bookExpected, bookActual);
    }

    @Test
    public void testFindByLanguage() {
        List<Book> bookActual = service.findByLanguage(books.get(0).getLanguage());
        List<Book> bookExpected = books.stream()
                .filter(b -> b.getLanguage().equals(books.get(0).getLanguage()))
                .collect(Collectors.toList());

        assertEquals(bookExpected, bookActual);
    }

    @Test
    public void testFindByCategory() {
        Category category = books.get(0).getCategories().get(0);
        List<Book> bookActual = service.findByCategory(category);
        List<Book> bookExpected = books.stream()
                .filter(b -> b.getCategories().contains(category))
                .collect(Collectors.toList());

        assertEquals(bookExpected, bookActual);
    }

    @Test
    public void testFindByAuthor() {
        Author author = books.get(0).getAuthors().get(0);
        List<Book> bookActual = service.findByAuthor(author);
        List<Book> bookExpected = books.stream()
                .filter(b -> b.getAuthors().contains(author))
                .collect(Collectors.toList());

        assertEquals(bookExpected, bookActual);
    }
}
