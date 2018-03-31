package com.mashjulal.java.bookie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USER")
@Data
@EqualsAndHashCode(exclude = {"books"})
@ToString(exclude = {"books"})
public class User implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "LOGIN", length = 50)
    private String login;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PHOTO", columnDefinition = "mediumblob")
    private byte[] photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "BOOK_USER",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private List<Book> books;

    public User() {}

    public User(
            long id,
            @NotNull String login,
            @NotNull String password,
            @NotNull String email,
            byte[] photo,
            List<Book> books
    ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.photo = photo;
        this.books = books;
    }

    public User(
            @NotNull String login,
            @NotNull String password,
            @NotNull String email,
            byte[] photo,
            List<Book> books
    ) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.photo = photo;
        this.books = books;
    }
}
