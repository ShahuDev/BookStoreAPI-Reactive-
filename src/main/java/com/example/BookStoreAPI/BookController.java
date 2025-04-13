package com.example.BookStoreAPI;

import com.example.BookStoreAPI.Entities.Book;
import com.example.BookStoreAPI.Services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @GetMapping("/all")
    public Mono<ResponseEntity<Flux<Book>>> getAllBooks(){
        return bookServices.getAllBooks();
    }
    @PostMapping("/addBook")
    public Mono<ResponseEntity<Book>> addBook(@RequestBody Book book){
        return bookServices.createBook(book);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Book>> getBookById(@PathVariable int id){
        return bookServices.getBookById(id);
    }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Book>> updateBook(@PathVariable int id,@RequestBody Book book){
        return bookServices.updateBook(id,book);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<HttpStatus>> deleteBook(@PathVariable int id){
        return bookServices.deleteBook(id);
    }
    @GetMapping("/search")
    public Mono<ResponseEntity<Flux<Book>>> searchBook(@RequestParam String Name){
        return bookServices.searchBook(Name);
    }
}
