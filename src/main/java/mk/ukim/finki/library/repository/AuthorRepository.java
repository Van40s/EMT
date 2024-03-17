package mk.ukim.finki.library.repository;

import mk.ukim.finki.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
