package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.model.dto.CountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Optional<Country> addCountry(String name, String continent);

    Country findCountry(Long id);

    List<Country> listAll();

}
