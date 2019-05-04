package org.bookzone.core.repository;

import org.bookzone.core.model.Book;
import org.bookzone.core.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
