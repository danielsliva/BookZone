package org.bookzone.core.service;

import org.bookzone.core.model.Book;
import org.bookzone.core.model.Comment;
import org.bookzone.core.repository.BookRepository;
import org.bookzone.core.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public Comment save(Comment comment){
        return repository.save(comment);
    }

    public List<Comment> findAllComments(){
        List<Comment> comments = new ArrayList<>();
        for(Comment comment: repository.findAll()){
            comments.add(comment);
        }
        return comments;
    }
}
