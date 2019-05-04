package org.bookzone.core.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "rates_books",
            joinColumns = @JoinColumn(
                    name = "rate_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "book_id",referencedColumnName = "id"))
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "rates_users",
            joinColumns = @JoinColumn(
                    name = "rates_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",referencedColumnName = "id"))
    private User user;
}
