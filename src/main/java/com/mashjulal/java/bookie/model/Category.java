package com.mashjulal.java.bookie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
@Data
@EqualsAndHashCode(exclude = {"books"})
public class Category implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "NAME", length = 50)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Book> books;

    public Category() {}

    public Category(long id, @NotNull String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Category(long id, @NotNull String name) {
        this(id, name, new ArrayList<>());
    }

    public Category(@NotNull String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Category(@NotNull String name) {
        this(name, new ArrayList<>());
    }
}
