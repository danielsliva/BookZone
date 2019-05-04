package org.bookzone.core.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String content;
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "comments_books",
            joinColumns = @JoinColumn(
                    name = "comment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "book_id",referencedColumnName = "id"))
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "comments_users",
            joinColumns = @JoinColumn(
                    name = "comment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",referencedColumnName = "id"))
    private User user;

}
