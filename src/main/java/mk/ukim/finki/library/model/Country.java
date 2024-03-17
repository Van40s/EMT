package mk.ukim.finki.library.model;


//За секоја земја се чуваат податоците: id (Long), name (String), continent (String).


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String continent;

    public Country(){

    }

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}
