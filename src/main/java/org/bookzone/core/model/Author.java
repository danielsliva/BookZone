package org.bookzone.core.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String nationality;
}
