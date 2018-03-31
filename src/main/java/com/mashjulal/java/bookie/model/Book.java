package com.mashjulal.java.bookie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "BOOK")
@Data
@EqualsAndHashCode(exclude = {"authors", "categories", "language"})
@ToString(exclude = {"authors", "categories", "language"})
public class Book implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "TITLE", length = 75)
    private String title;

    @NotNull
    @Column(name = "PAGES_COUNT")
    private int pagesCount;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "IMAGE", columnDefinition = "mediumblob")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_ID")
    private Language language;

    @ManyToMany
    @JoinTable(
            name = "BOOK_AUTHOR",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID")
    )
    private List<Author> authors;

    @ManyToMany
    @JoinTable(
            name = "BOOK_CATEGORY",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    private List<Category> categories;

    public Book() {}

    public Book(
            long id,
            @NotNull String title,
            @NotNull int pagesCount,
            String description,
            @NotNull Language language,
            byte[] image,
            List<Author> authors,
            List<Category> categories
    ) {
        this(title, pagesCount, description, language, image, authors, categories);
        this.id = id;
    }

    public Book(
            @NotNull String title,
            @NotNull int pagesCount,
            String description,
            @NotNull Language language,
            byte[] image,
            List<Author> authors,
            List<Category> categories
    ) {
        this.title = title;
        this.pagesCount = pagesCount;
        this.description = description;
        this.language = language;
        this.image = image;
        this.authors = authors;
        this.categories = categories;
    }
}
