package com.mashjulal.java.bookie.utils;

import com.mashjulal.java.bookie.model.*;
import lombok.Data;

import java.util.*;

public class DataSetInitializer {

    public static List<Author> getAuthors() {
        return new Data().authors;
    }

    public static List<Book> getBooks() {
        return new Data().books;
    }

    public static List<Category> getCategories() {
        return new Data().categories;
    }

    public static List<Language> getLanguages() {
        return new Data().languages;
    }

    public static List<User> getUsers() {
        return new Data().users;
    }

    private static class Data {
        private List<Author> authors;
        private List<Book> books;
        private List<Category> categories;
        private List<Language> languages;
        private List<User> users;

        Data() {
            this.authors = prepareAuthors();
            this.books = prepareBooks();
            this.categories = prepareCategories();
            this.languages = prepareLanguages();
            this.users = prepareUsers();

            authors.get(0).getBooks().addAll(books);
            authors.get(1).getBooks().add(books.get(1));

            books.get(0).setLanguage(languages.get(0));
            books.get(0).getCategories().addAll(categories.subList(0, 1));
            books.get(0).getAuthors().add(authors.get(0));

            books.get(1).setLanguage(languages.get(1));
            books.get(1).getCategories().add(categories.get(1));
            books.get(1).getAuthors().addAll(authors.subList(0, 1));

            categories.get(0).getBooks().add(books.get(0));
            categories.get(1).getBooks().addAll(books);

            users.get(0).getBooks().addAll(books);
            users.get(1).getBooks().add(books.get(0));
        }

        private List<Author> prepareAuthors() {
            List<Author> authors = new ArrayList<>();

            authors.add(new Author(
                    1,
                    "FirstName1",
                    "LastName1",
                    null,
                    null,
                    new ArrayList<>())
            );
            authors.add(new Author(
                    2,
                    "FirstName2",
                    "LastName2",
                    null,
                    null,
                    new ArrayList<>())
            );

            return authors;
        }

        private List<Category> prepareCategories() {
            List<Category> categories = new ArrayList<>();

            categories.add(new Category(1, "Pop Science"));
            categories.add(new Category(2, "Classic"));
            categories.add(new Category(3, "Thriller"));

            return categories;
        }

        private List<Language> prepareLanguages() {
            List<Language> languages = new ArrayList<>();

            languages.add(new Language(1,"Russian"));
            languages.add(new Language(2, "English"));

            return languages;
        }

        private List<Book> prepareBooks() {

            Book book1 = new Book(
                    1,
                    "Title1",
                    10,
                    null,
                    null,
                    null,
                    new ArrayList<>(),
                    new ArrayList<>()
            );

            Book book2 = new Book(
                    2,
                    "Title2",
                    20,
                    null,
                    null,
                    null,
                    new ArrayList<>(),
                    new ArrayList<>()
            );

            return new ArrayList<>(Arrays.asList(book1, book2));
        }

        private List<User> prepareUsers() {
            List<User> users = new ArrayList<>();

            users.add(new User(
                    1,
                    "User1",
                    "pass",
                    "email1",
                    null,
                    new ArrayList<>())
            );
            users.add(new User(
                    2,
                    "User2",
                    "pass",
                    "email2",
                    null,
                    new ArrayList<>())
            );
            return users;
        }
    }
}
