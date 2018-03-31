package com.mashjulal.java.bookie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "AUTHOR")
@Data
@EqualsAndHashCode(exclude = {"books"})
@ToString(exclude = {"books"})
public class Author implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "PHOTO", columnDefinition = "mediumblob")
    private byte[] photo;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books;

    public Author() { }

    public Author(long id, @NotNull String firstName, @NotNull String lastName, String description, byte[] photo, List<Book> books) {
        this(firstName, lastName, description, photo, books);
        this.id = id;
    }

    public Author(@NotNull String firstName, @NotNull String lastName, String description, byte[] photo, List<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.photo = photo;
        this.books = books;
    }
}
