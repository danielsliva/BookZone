package org.bookzone.core.service;

import org.bookzone.core.model.Book;
import org.bookzone.core.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Book save(Book book){
        return repository.save(book);
    }

    public Book findByTitle(String title){
        return repository.findByTitle(title);
    }

    public List<Book> findAllBooks(){
        List<Book> books = new ArrayList<>();
        for(Book book: repository.findAll()){
            books.add(book);
        }
        return books;
    }

    public Book findById(Long id){
        return repository.findById(id).get();
    }
}
