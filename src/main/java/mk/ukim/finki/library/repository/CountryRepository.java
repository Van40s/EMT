package mk.ukim.finki.library.repository;

import mk.ukim.finki.library.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
