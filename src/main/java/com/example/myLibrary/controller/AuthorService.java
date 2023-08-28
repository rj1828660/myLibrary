package com.example.myLibrary.controller;

import com.example.myLibrary.models.Author;

import com.example.myLibrary.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api")
public class AuthorService {
    @Autowired
    AuthorRepo authorRepo;



   @GetMapping("/allAuthors")
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    @PostMapping("/authors")
    public ResponseEntity<?> createAuthor(@Valid @RequestBody Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Author savedAuthor = authorRepo.save(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAuthorsByNameRegex(@RequestParam String nameRegex) {
        List<Author> authors = authorRepo.findByAuthorNameRegex(nameRegex);
        return ResponseEntity.ok(authors);
    }


}
