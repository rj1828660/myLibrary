package com.example.myLibrary;

import com.example.myLibrary.models.Author;
import com.example.myLibrary.models.Book;
import com.example.myLibrary.repository.AuthorRepository;
import com.example.myLibrary.repository.BookRepository;
import com.example.myLibrary.service.AuthorService;
import com.example.myLibrary.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
class MyLibraryApplicationTests {
	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;
	@MockBean
	private BookRepository bookRepository;
	@MockBean
	private AuthorRepository authorRepository;

	@Test
	public void getAllBooksTest() {
		when(bookRepository.findAll()).thenReturn(Stream.of(new Book("2", "book2", 2, "102", "general Knowledge"), new Book("1", "book1", 10, "100", "comic")).collect(Collectors.toList()));
		assertEquals(2, bookService.getAllBooks().size());
	}

	@Test
	public void getBooksByGenreTest() {
		String genre = "comic";
		when(bookRepository.findByGenre(genre)).thenReturn(Stream.of(new Book("1", "book1", 10, "100", "comic")).collect(Collectors.toList()));
		assertEquals(1, bookService.getBooksByGenre(genre).size());
	}

	@Test
	public void getBooksByGenreAndCopiesAvailableTest() {
		String genre = "comic";
		int copies = 2;
		when(bookRepository.findBooksByGenreAndCopiesAvailable(genre, copies)).thenReturn(Stream.of(new Book("2", "book2", 2, "102", "General Knowledge")).collect(Collectors.toList()));
		assertEquals(1, bookService.getBooksByGenreAndCopiesAvailable(genre, copies).size());
	}

	@Test
	public void createBookTest() {
		Book book = new Book("1", "book1", 10, "100", "comic");
		when(bookRepository.save(book)).thenReturn(book);
		assertEquals(book, bookService.createBook(book));
	}

	@Test
	public void getAllAuthorsTest() {
		when(authorRepository.findAll()).thenReturn(Stream.of(new Author("1", "author1", null), new Author("2", "author2", null)).collect(Collectors.toList()));
		assertEquals(2, authorService.getAllAuthors().size());
	}

	@Test
	public void createAuthorTest() {
		Author author = new Author("1", "author1", null);
		when(authorRepository.save(author)).thenReturn(author);

		assertEquals(author, authorService.createAuthor(author));
	}
}