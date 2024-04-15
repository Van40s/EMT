package mk.ukim.finki.library.repository;

import mk.ukim.finki.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
