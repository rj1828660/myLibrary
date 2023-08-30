package com.example.myLibrary.repository;


import com.example.myLibrary.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByGenre(String genre);
    @Query(value = "{'genre': ?0, 'copiesAvailable': {$gt: ?1}}")
    List<Book> findBooksByGenreAndCopiesAvailable(String genre, int copiesAvailable);

    @Query(value = "{'id': ?0}")
    Book findBookById(String id);
}