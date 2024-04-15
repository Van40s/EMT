package mk.ukim.finki.library.model.dto;


import lombok.Data;

@Data
public class BookDTO {
    String name;
    Long categoryId;
    Long authorId;
    Integer availableCopies;
}
