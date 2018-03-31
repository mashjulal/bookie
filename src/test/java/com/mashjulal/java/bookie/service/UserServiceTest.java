package com.mashjulal.java.bookie.service;

import com.mashjulal.java.bookie.BookieApplication;
import com.mashjulal.java.bookie.model.User;
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
public class UserServiceTest extends TestCase {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserService service;

    private List<User> users;


    @Before
    public void setup() {
        users = DataSetInitializer.getUsers();
    }

    @Test
    public void testFindById() {
        User userActual = service.findById(users.get(0).getId());
        User userExpected = users.get(0);

        assertEquals(userExpected, userActual);
        assertEquals(userExpected.getBooks().size(), userActual.getBooks().size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindById_NotFound() {
        service.findById(-1L);
    }

    @Test
    public void testCount() {
        long countActual = service.count();
        long countExpected = users.size();

        assertEquals(countExpected, countActual);
    }

    @Test
    public void testExistsById_True() {
        boolean existsActual = service.existsById(users.get(0).getId());
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
        List<User> usersActual = (List<User>) service.findAll();
        List<User> usersExpected = users;

        assertEquals(usersExpected, usersActual);
    }

    @Test
    public void testFindAllById() {
        List<User> usersActual = (List<User>) service.findAllById(
                users.subList(0, 2).stream().map(User::getId).collect(Collectors.toList()));
        List<User> usersExpected = users.subList(0, 2);

        assertEquals(usersExpected, usersActual);
    }

    @Test
    public void testDeleteById() {
        service.deleteById(users.get(0).getId());
        users.remove(0);

        List<User> usersActual = users;
        List<User> usersExpected = (List<User>) service.findAll();

        assertEquals(usersExpected, usersActual);
    }

    @Test
    public void testFindByLogin() {
        List<User> usersActual = service.findByLogin("User");
        List<User> usersExpected = users;

        assertEquals(usersExpected, usersActual);
    }
}
