package mk.ukim.finki.library.service.implementation;

import jakarta.transaction.Transactional;
import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.dto.AuthorDTO;
import mk.ukim.finki.library.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.library.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.repository.CountryRepository;
import mk.ukim.finki.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository){
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Author findAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(InvalidAuthorIdException::new);
    }

    @Override
    public Optional<Author> addAuthor(AuthorDTO authorDTO) {
        Author author = new Author(authorDTO.getFirstname(), authorDTO.getLastname(),
                countryRepository.findById(authorDTO.getCountryId()).orElseThrow(InvalidCountryIdException::new));

        return Optional.of(authorRepository.save(author));
    }
}
