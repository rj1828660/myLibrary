package com.example.myLibrary.repository;


import com.example.myLibrary.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepo extends MongoRepository<Book, String> {
    List<Book> findByGenre(String genre);
    @Query(value = "{'genre': ?0, 'copiesAvailable': {$gt: ?1}}")
    List<Book> findBooksByGenreAndCopiesAvailable(String genre, int copiesAvailable);
}