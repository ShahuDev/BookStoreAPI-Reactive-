package com.example.BookStoreAPI.Services;

import com.example.BookStoreAPI.Entities.Book;
import com.example.BookStoreAPI.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServices {

    @Autowired
    private BookRepository bookRepository;

    //method to create a book
    public Mono<ResponseEntity<Book>> createBook(Book book){
        return bookRepository.findByName(book.getName())
                .flatMap(data -> Mono.just(new ResponseEntity<>(book,HttpStatus.CONFLICT)))
                .switchIfEmpty(
                        bookRepository.save(book).map(book1-> new ResponseEntity<>(book1,HttpStatus.CREATED))
                );
    }
    // method to get the list of books.
    public Mono<ResponseEntity<Flux<Book>>>  getAllBooks(){
        var fluxBook= bookRepository.findAll();
        return fluxBook.hasElements().map(hasBooks ->           // hasElements() checks whether there are elements in the flux or not.
                {
                    if (hasBooks) {
                        return new ResponseEntity<>(fluxBook,HttpStatus.OK);
                    }
                    else{
                        return new ResponseEntity<>(Flux.empty(),HttpStatus.BAD_REQUEST);
                    }
                });
    }

    public Mono<ResponseEntity<Book>> getBookById(int id){
        var book = bookRepository.findById(id);
        return book.map(book1 -> new ResponseEntity<>(book1,HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }
    public Mono<ResponseEntity<Book>> updateBook(int id,Book book){
        var updatedBook = bookRepository.findById(book.getBookId());
        return updatedBook.flatMap(ub->
                {
                    return bookRepository.save(ub);
                })
                .map(ub1->new ResponseEntity<>(ub1,HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }
    public Mono<ResponseEntity<HttpStatus>> deleteBook(int id) {
        return bookRepository.findById(id)
                .flatMap(existingBook ->
                        bookRepository.delete(existingBook)
                                .then(Mono.just(new ResponseEntity<HttpStatus>(HttpStatus.OK)))

                )
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }



    public Mono<ResponseEntity<Flux<Book>>> searchBook(String query) {
        Flux<Book> books = bookRepository.findByNameContainingIgnoreCase(query);
        return books.hasElements()
                .map(hasBooks -> hasBooks
                        ? new ResponseEntity<>(books, HttpStatus.OK)
                        : new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
