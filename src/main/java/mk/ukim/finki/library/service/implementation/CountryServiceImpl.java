package mk.ukim.finki.library.service.implementation;

import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.library.repository.CountryRepository;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }


    @Override
    public Optional<Country> addCountry(String name, String continent) {
        Country country = new Country(name, continent);

        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Country findCountry(Long id) {
        return countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new);
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }
}
