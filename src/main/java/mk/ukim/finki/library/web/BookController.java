package mk.ukim.finki.library.web;


import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.dto.BookDTO;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService){
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAllBooks();

        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        return ResponseEntity.ok().body(bookService.findBook(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO dto){
        if(dto == null) {
            return ResponseEntity.notFound().build();
        }

        if(authorService.findAuthor(dto.getAuthorId()) == null) {
            return ResponseEntity.notFound().build();
        }



        return this.bookService.addBook(dto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        if(id == null) {
            return ResponseEntity.notFound().build();
        }

        if(bookService.findBook(id) == null) {
            return ResponseEntity.notFound().build();
        }

        return this.bookService.deleteBook(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit-book/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        if(bookDTO == null) {
            return ResponseEntity.notFound().build();
        }

        if(authorService.findAuthor(bookDTO.getAuthorId()) == null || bookService.findBook(id) == null) {
            return ResponseEntity.notFound().build();
        }

        return bookService.editBook(id, bookDTO).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/mark-rented/{id}")
    public ResponseEntity<Book> markRented(@PathVariable Long id){
        if(id == null) {
            return ResponseEntity.notFound().build();
        }

        if(bookService.findBook(id) == null) {
            return ResponseEntity.notFound().build();
        }

        return this.bookService.markRented(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
