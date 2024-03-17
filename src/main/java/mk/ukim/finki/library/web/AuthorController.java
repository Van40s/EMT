package mk.ukim.finki.library.web;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.model.dto.AuthorDTO;
import mk.ukim.finki.library.repository.CountryRepository;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.CountryService;
import mk.ukim.finki.library.service.implementation.AuthorServiceImpl;
import mk.ukim.finki.library.service.implementation.CountryServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorController(AuthorServiceImpl authorservice, CountryServiceImpl countryService){
        this.authorService = authorservice;
        this.countryService = countryService;
    }


    @PostMapping("/add-author")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDTO authorDTO){
        if(authorDTO == null) {
            return ResponseEntity.notFound().build();
        }

        if(countryService.findCountry(authorDTO.getCountryId()) == null) {
            return ResponseEntity.notFound().build();
        }


        return authorService.addAuthor(authorDTO)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
