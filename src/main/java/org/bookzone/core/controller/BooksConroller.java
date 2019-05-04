package org.bookzone.core.controller;

import org.bookzone.core.CheckUser;
import org.bookzone.core.model.Book;
import org.bookzone.core.model.Comment;
import org.bookzone.core.model.Rate;
import org.bookzone.core.model.User;
import org.bookzone.core.service.BookService;
import org.bookzone.core.service.CommentService;
import org.bookzone.core.service.RateService;
import org.bookzone.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class BooksConroller {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RateService rateService;

    @Autowired
    private CheckUser checkUser;

    @ModelAttribute("find")
    public Book bookDto(){
        return new Book();
    }

    @ModelAttribute("commentObj")
    public Comment commentDto(){
        return new Comment();
    }

    @ModelAttribute("rateObj")
    public Rate rateDto(){
        return new Rate();
    }

    @GetMapping("/books")
    public String showBooks(Model model){
        List<Book> books = bookService.findAllBooks();
        addModelAttributes(model,"Books",false,books);
        return "home";
    }

    @PostMapping("/books")
    public String showBook(@ModelAttribute("find") Book findBook, Model model){
        if(findBook.getTitle().equals("")){
            return "redirect:/books";
        }
        List<Book> books = new ArrayList<>();
        for(Book book: bookService.findAllBooks()){
            if(book.getTitle().toLowerCase().contains(findBook.getTitle().toLowerCase())){
                books.add(book);
            }
        }
        addModelAttributes(model,"Books",false,books);
        return "home";
    }

    @GetMapping("/mybooks")
    public String showMyBooks(Model model){
        User user = userService.findByEmail(checkUser.getUsername());
        List<Book> books = new ArrayList<>();
        for(Book book1: bookService.findAllBooks()){
            for(User user1: book1.getUsers()){
                if(user1.getEmail().equals(user.getEmail())){
                    books.add(book1);
                }
            }
        }
        addModelAttributes(model,"Your books",true,books);
        return "home";
    }

    public void addModelAttributes(Model model, String title, boolean mybook, List<Book> books){
        model.addAttribute("bookheader",title);
        model.addAttribute("mybooks",mybook);
        model.addAttribute("nameOfUser",checkUser.getUsername());
        model.addAttribute("books",books);
    }

    @GetMapping("/bestbooks")
    public String showbestBook(Model model){
        List<Book> books = bookService.findAllBooks();
        Collections.sort(books, (b1,b2)->{
            float averageBook1 = getRatesAverage(getRates(b1));
            float averageBook2 = getRatesAverage(getRates(b2));
            if(averageBook1 == averageBook2){
                return 0;
            }else if( averageBook1 > averageBook2 ){
                return -1;
            }else{
                return 1;
            }
        });
        addModelAttributes(model,"Best books",false,books);
        return "home";
    }

    public List<Comment> getComments(Book book){
        List<Comment> comments = new ArrayList<>();
        for(Comment comment: commentService.findAllComments()){
            if(comment.getBook().getTitle().equals(book.getTitle())){
                comments.add(comment);
            }
        }
        return comments;
    }

    public List<Rate> getRates(Book book){
        List<Rate> rates = new ArrayList<>();
        for(Rate rate: rateService.findAllRates()){
            if(rate.getBook().getTitle().equals(book.getTitle())){
                rates.add(rate);
            }
        }
        return rates;
    }

    public float getRatesAverage(List<Rate> rates){
        if(rates.size()==0){
            return 0;
        }
        float average = 0;
        for(Rate rate: rates){
            average+=rate.getValue();
        }
        return average/rates.size();
    }

    @GetMapping("/books/{id}")
    public String showBookDetails(@PathVariable("id") Long id, Model model){
        Book book = bookService.findById(id);
        if(book==null){
            return "error";
        }
        model.addAttribute("comments",getComments(book));
        model.addAttribute("rates",getRates(book));
        model.addAttribute("averagerates", getRatesAverage(getRates(book)));
        model.addAttribute("book",book);
        model.addAttribute("mybooks",false);
        model.addAttribute("nameOfUser",checkUser.getUsername());
        return "index";
    }

    @PostMapping("/comment/{id}")
    public String addComment(@ModelAttribute("commentObj") Comment commentObj, @PathVariable("id") Long id){
        Comment comment = new Comment();
        comment.setUser(userService.findByEmail(checkUser.getUsername()));
        comment.setContent(commentObj.getContent());
        comment.setDate(new Date());
        comment.setBook(bookService.findById(id));
        commentService.save(comment);
        return "redirect:/books/"+bookService.findById(id).getId(); //"index";
    }

    @PostMapping("/rate/{id}")
    public String addRate(@ModelAttribute("rateObj") Rate rateObj, @PathVariable("id") Long id){
        for(Rate rate: rateService.findAllRates()){
            if(rate.getUser().getEmail().equals(checkUser.getUsername()) && rate.getBook().equals(bookService.findById(id))){
                return "redirect:/books/"+bookService.findById(id).getId();
            }
        }
        if(rateObj.getValue()==0.00){return "redirect:/books/"+bookService.findById(id).getId();}
        Rate rate = new Rate();
        rate.setUser(userService.findByEmail(checkUser.getUsername()));
        rate.setValue(rateObj.getValue());
        rate.setBook(bookService.findById(id));
        rateService.save(rate);
        return "redirect:/books/"+bookService.findById(id).getId();
    }


    @PostMapping("/addbook")
    public String addBook(@ModelAttribute("find") Book findBook){
        Book book = bookService.findByTitle(findBook.getTitle());
        User user = userService.findByEmail(checkUser.getUsername());
        Collection<User> users = book.getUsers();
        for(User user1: users){
            if(user1.getEmail().equals(user.getEmail())){
                return "redirect:/books";
            }
        }
        users.add(user);
        book.setUsers(users);
        bookService.save(book);
        return "redirect:/books";
    }

    @PostMapping("/removebook")
    public String removeBook(@ModelAttribute("find") Book findBook){
        Book book = bookService.findByTitle(findBook.getTitle());
        User user = userService.findByEmail(checkUser.getUsername());
        Collection<User> users = book.getUsers();
        users.remove(user);
        book.setUsers(users);
        bookService.save(book);
        return "redirect:/mybooks";
    }


}
