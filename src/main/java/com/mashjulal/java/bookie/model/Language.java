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
@Table(name = "LANGUAGE")
@Data
@EqualsAndHashCode(exclude = {"books"})
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "NAME", length = 50)
    private String name;

    @OneToMany(mappedBy = "language")
    private List<Book> books;

    public Language() {}

    public Language(long id, @NotNull String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Language(long id, @NotNull String name) {
        this(id, name, new ArrayList<>());
    }

    public Language(@NotNull String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Language(@NotNull String name) {
        this(name, new ArrayList<>());
    }
}

