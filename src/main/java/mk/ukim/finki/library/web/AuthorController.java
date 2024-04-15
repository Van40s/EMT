package mk.ukim.finki.library.web;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.dto.AuthorDTO;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.CountryService;
import mk.ukim.finki.library.service.implementation.AuthorServiceImpl;
import mk.ukim.finki.library.service.implementation.CountryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorController(AuthorServiceImpl authorService, CountryServiceImpl countryService){
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Author>> getAll(){


        return ResponseEntity.ok().body(authorService.listAll());
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
