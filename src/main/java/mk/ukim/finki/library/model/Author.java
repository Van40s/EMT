package mk.ukim.finki.library.model;

//За секој автор пак се чуваат податоците: id (Long), name (String), surname (String), country (Country).


import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "authors")
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;

    public Author() {

    }

    public Author(String firstname, String lastname, Country country) {
        this.name = firstname;
        this.surname = lastname;
        this.country = country;
    }
}
