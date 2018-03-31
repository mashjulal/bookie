package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.BookieApplication;
import com.mashjulal.java.bookie.model.Language;
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
public class LanguageServiceTest extends TestCase {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LanguageService service;

    private List<Language> languages;


    @Before
    public void setup() {
        languages = DataSetInitializer.getLanguages();
    }

    @Test
    public void testFindById() {
        Language authorActual = service.findById(languages.get(0).getId());
        Language authorExpected = languages.get(0);

        assertEquals(authorExpected, authorActual);
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindById_NotFound() {
        service.findById(-1L);
    }

    @Test
    public void testCount() {
        long countActual = service.count();
        long countExpected = languages.size();

        assertEquals(countExpected, countActual);
    }

    @Test
    public void testExistsById_True() {
        boolean existsActual = service.existsById(languages.get(0).getId());
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
        List<Language> languagesActual = (List<Language>) service.findAll();
        List<Language> languagesExpected = languages;

        assertEquals(languagesExpected, languagesActual);
    }

    @Test
    public void testFindAllById() {
        List<Language> languagesActual = (List<Language>) service.findAllById(
                languages.subList(0, 2).stream().map(Language::getId).collect(Collectors.toList()));
        List<Language> languagesExpected = languages.subList(0, 2);

        assertEquals(languagesExpected, languagesActual);
    }

    @Test
    public void testDeleteById() {
        service.deleteById(languages.get(0).getId());
        languages.remove(0);

        List<Language> languagesActual = languages;
        List<Language> languagesExpected = (List<Language>) service.findAll();

        assertEquals(languagesExpected, languagesActual);
    }
}
