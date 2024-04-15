package mk.ukim.finki.library.service.implementation;

import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.dto.BookDTO;
import mk.ukim.finki.library.model.event.BookCreatedEvent;
import mk.ukim.finki.library.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.library.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.library.model.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.repository.BookRepository;
import mk.ukim.finki.library.repository.CategoryRepository;
import mk.ukim.finki.library.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ApplicationEventPublisher applicationEventPublisher, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Optional<Book> addBook(BookDTO bookDTO) {
        Book newBook = new Book(bookDTO.getName(),
                categoryRepository.findById(bookDTO.getCategoryId()).orElseThrow(InvalidCategoryIdException::new),
                authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(InvalidAuthorIdException::new),
                bookDTO.getAvailableCopies());

        if(newBook.getAvailableCopies()>0){
            newBook.setRented(false);
        }

        applicationEventPublisher.publishEvent(new BookCreatedEvent(newBook));


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
        book.setCategory(categoryRepository.findById(bookDTO.getCategoryId()).orElseThrow(InvalidCategoryIdException::new));
        book.setAvailableCopies(bookDTO.getAvailableCopies());

        book.setRented(book.getAvailableCopies() <= 0);

        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> markRented(Long id) {
        Book book = this.findBook(id);
        if(book.getRented()){
            return Optional.of(bookRepository.save(book));
        }

        int availableCopies = book.getAvailableCopies() - 1;

        book.setAvailableCopies(availableCopies);

        if(availableCopies == 0){
            book.setRented(true);
        }

        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Book findBook(Long id) {
        return bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
