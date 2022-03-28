package com.example.demo.api;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/vi/book")
@RestController
public class BookController {
    private final BookService bookService;

    @Autowired //injects service into controller
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @PostMapping
    public void addBook(@Valid @NonNull @RequestBody Book book){
        bookService.addBook(book);
    }
    @GetMapping //method will be served as get request
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping(path="{id}")
    public Book getBookById(@PathVariable("id") UUID id){
        return bookService.getBookById(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deleteBookByID(@PathVariable("id")UUID id){
        bookService.deletePerson(id);
    }

    @PutMapping(path="{id}")
    public void updateBook(@PathVariable("id") UUID id,@Valid @NonNull @RequestBody Book bookToUpdate){
        bookService.updateBook(id,bookToUpdate);
    }
}
