package mk.ukim.finki.library.model.dto;


import lombok.Data;
import mk.ukim.finki.library.model.Category;

@Data
public class BookDTO {
    String name;
    Category category;
    Long authorId;
    Integer availableCopies;
}
