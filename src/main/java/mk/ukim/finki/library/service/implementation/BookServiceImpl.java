package mk.ukim.finki.library.service.implementation;

import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.dto.BookDTO;
import mk.ukim.finki.library.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.library.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.repository.BookRepository;
import mk.ukim.finki.library.service.BookService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Book> addBook(BookDTO bookDTO) {
        Book newBook = new Book(bookDTO.getName(),
                bookDTO.getCategory(),
                authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(InvalidAuthorIdException::new),
                bookDTO.getAvailableCopies());

        return Optional.of(bookRepository.save(newBook));
    }

    @Override
    public Optional<Book> deleteBook(Long id) {
        Book book = this.findBook(id);
        bookRepository.deleteById(id);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> editBook(Long bookId, BookDTO bookDTO) {
        Book book = this.findBook(bookId);
        book.setName(bookDTO.getName());
        book.setAuthor(authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(InvalidAuthorIdException::new));
        book.setCategory(bookDTO.getCategory());
        book.setAvailableCopies(book.getAvailableCopies());

        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> markRented(Long id) {
        Book book = this.findBook(id);
        book.setRented(!book.getRented());
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Book findBook(Long id) {
        return bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
    }
}
