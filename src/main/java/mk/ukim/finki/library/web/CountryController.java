package mk.ukim.finki.library.web;


import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.service.CountryService;
import mk.ukim.finki.library.service.implementation.CountryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryServiceImpl countryService){
        this.countryService = countryService;
    }


    @GetMapping
    public ResponseEntity<Country> addCountry(@RequestParam String name, @RequestParam String continent){
        if(name == null || continent == null){
            return ResponseEntity.badRequest().build();
        }

        return this.countryService.addCountry(name, continent)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<Country>> getCountries() {
        var countries = countryService.listAll();

        return ResponseEntity.ok(countries);
    }


}
