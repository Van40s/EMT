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

    @ManyToOne()
    private Category category;

    @ManyToOne()
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
        this.rented = true;
    }
}

//    Во рамки на апликацијата се чуваат следните податоци за книгите: id (Long), name (String), category (enum), author (Author), availableCopies (Integer).

