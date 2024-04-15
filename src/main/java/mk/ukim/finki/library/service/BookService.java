package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> addBook(BookDTO bookDto);

    Optional<Book> deleteBook(Long id);

    Optional<Book> editBook(Long bookId, BookDTO bookDTO);

    Optional<Book> markRented(Long id);

    Book findBook(Long id);

    List<Book> getAllBooks();
}
