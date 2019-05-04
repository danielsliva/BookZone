package org.bookzone.core.repository;

import org.bookzone.core.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByTitle(String title);
}
