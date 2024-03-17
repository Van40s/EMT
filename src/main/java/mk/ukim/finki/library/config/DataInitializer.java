package mk.ukim.finki.library.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.library.model.Category;
import mk.ukim.finki.library.model.dto.AuthorDTO;
import mk.ukim.finki.library.model.dto.BookDTO;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.BookService;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final AuthorService authorService;

    private final BookService bookService;

    private final CountryService countryService;


    public DataInitializer(AuthorService authorService, BookService bookService, CountryService countryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void initData() {

        for(int i=0; i<5; i++){
            countryService.addCountry("Country" + i, "Continent" + i);
        }

//        for(int i=0; i<3; i++){
//            AuthorDTO authorDTO = new AuthorDTO();
//            authorDTO.setCountryId(Long.valueOf(i + 1));
//            authorDTO.setFirstname("Firstname" + i);
//            authorDTO.setLastname("Lastname" + i);
//            authorService.addAuthor(authorDTO);
//        }
//
//        for(int i=0; i<2; i++){
//            BookDTO book = new BookDTO();
//            book.setName("Book" + i);
//            book.setAvailableCopies(i + 10);
//            book.setCategory(Category.HISTORY);
//            book.setAuthorId(Long.valueOf(i + 1));
//            bookService.addBook(book);
//        }
    }

}
