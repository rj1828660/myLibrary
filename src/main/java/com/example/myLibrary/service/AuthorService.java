package com.example.myLibrary.service;

import com.example.myLibrary.models.Author;
import com.example.myLibrary.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {



    @Autowired
    public AuthorRepository authorRepository;
    public Author findAuthorByName(String name){
        return authorRepository.findByName(name);

    }
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }
    public List<Author> getAuthorsByNameRegex(String nameRegex){
        return authorRepository.findByAuthorNameRegex(nameRegex);
    }
}
