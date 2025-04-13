package com.example.BookStoreAPI.Repository;

import com.example.BookStoreAPI.Entities.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book,Integer> {
    Mono<Book> findByName(String name);

    Flux<Book> findByNameContainingIgnoreCase(String query);
}
