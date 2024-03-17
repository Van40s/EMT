package mk.ukim.finki.library.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    private int availableCopies;

    private Boolean rented;

    public Book(){

    }


    public Book(String name, Category category, Author author, int availableCopies){
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.rented = false;
    }
}

//    Во рамки на апликацијата се чуваат следните податоци за книгите: id (Long), name (String), category (enum), author (Author), availableCopies (Integer).

