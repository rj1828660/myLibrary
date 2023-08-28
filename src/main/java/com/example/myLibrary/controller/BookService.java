package com.example.myLibrary.controller;

import com.example.myLibrary.models.Book;
import com.example.myLibrary.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")

@Validated

public class BookService {
    @Autowired
    private BookRepo bookRepo;


    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genre) {
        List<Book> books = bookRepo.findByGenre(genre);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/books/condition")
    public ResponseEntity<List<Book>> getBooksByGenreAndCopiesAvailable(
            @RequestParam String genre,
            @RequestParam int copiesAvailable) {
        List<Book> books = bookRepo.findBooksByGenreAndCopiesAvailable(genre, copiesAvailable);
        return ResponseEntity.ok(books);
    }


    @PostMapping("/books")
    public ResponseEntity<?> createBook(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Book savedBook = bookRepo.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

}
