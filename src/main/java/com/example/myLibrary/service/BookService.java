package com.example.myLibrary.service;


import com.example.myLibrary.models.Book;
import com.example.myLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;


    //get all books
   public List<Book> getAllBooks(){

       return bookRepository.findAll();
    }
    //get books of give genre
   public List<Book> getBooksByGenre(String genre){
       return bookRepository.findByGenre(genre);
   }
   //get all books of given genre and copies greater than input value
   public List<Book> getBooksByGenreAndCopiesAvailable(String genre, int copiesAvailable){
       return bookRepository.findBooksByGenreAndCopiesAvailable(genre, copiesAvailable);
   }

   //get books by author name
    public Book getBooksById(String id){


        return bookRepository.findBookById(id);
    }
 //create book
   public Book createBook(Book book){
       return bookRepository.save(book);
   }




}
