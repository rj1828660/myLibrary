package com.example.myLibrary.controller;

import com.example.myLibrary.models.Author;
import com.example.myLibrary.models.Book;

import com.example.myLibrary.service.AuthorService;
import com.example.myLibrary.service.BookService;
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
public class Controller {


    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;


    @GetMapping("/getBooks")
    public ResponseEntity<List<Book>>getAllBooks(){
        List<Book>books=bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/byGenre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genre) {
        List<Book>books=bookService.getBooksByGenre(genre);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/books/byGenreAndCopies/")
    public ResponseEntity<List<Book>> getBooksByGenreAndCopiesAvailable(
            @RequestParam String genre,
            @RequestParam int copiesAvailable) {
        List<Book> books = bookService.getBooksByGenreAndCopiesAvailable(genre,copiesAvailable);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/books/byAuthorName")
    public ResponseEntity<List<Book>> getBooksByAuthorName(
            @RequestParam String name) {
        Author author=authorService.findAuthorByName(name);

        String id= author.getId();
          List<Book> book= bookService.getBooksByAuthorId(id);

        return ResponseEntity.ok(book);
    }

    @PostMapping("/books/createBook")
    public ResponseEntity<?> createBook(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Book savedBook =bookService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/getAuthors")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping("/createAuthors")
    public ResponseEntity<?> createAuthor(@Valid @RequestBody Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Author savedAuthor = authorService.createAuthor(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }
    @GetMapping("/authorsByName")
    public ResponseEntity<List<Author>> getAuthorsByNameRegex(@RequestParam String nameRegex) {
        List<Author> authors =authorService.getAuthorsByNameRegex(nameRegex);
        return ResponseEntity.ok(authors);
    }



}
