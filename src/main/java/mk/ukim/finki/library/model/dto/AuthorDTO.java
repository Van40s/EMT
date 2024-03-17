package mk.ukim.finki.library.model.dto;

import lombok.Data;

@Data
public class AuthorDTO {
    private String firstname;
    private String lastname;
    private Long countryId;
}
