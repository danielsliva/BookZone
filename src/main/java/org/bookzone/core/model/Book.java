package org.bookzone.core.model;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "book_cover")
    private String bookCover;
    private String title;
    @Lob
    private String description;
    @Column(name = "release_date")
    private Date releaseDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_users",
            joinColumns = @JoinColumn(
                    name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",referencedColumnName = "id"))
    private Collection<User> users;

}
