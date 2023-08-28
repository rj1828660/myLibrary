package com.example.myLibrary.repository;

import com.example.myLibrary.models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AuthorRepo extends MongoRepository<Author,String> {
    @Query("{'name': {$regex: ?0}}")
    List<Author> findByAuthorNameRegex(String nameRegex);
}
