package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.dto.AuthorDTO;

import java.util.Optional;

public interface AuthorService {

    Author findAuthor(Long id);

    Optional<Author> addAuthor(AuthorDTO authorDTO);

}
