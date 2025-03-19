package dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import rules.ValidBook;

import java.time.LocalDate;

//kolla igenom valid
@ValidBook()
public record CreateBook(
        @NotBlank(message = "Title is required") String title,
        @NotBlank(message = "Author is required") String author,
        @NotBlank(message = "Description is required") String description,
        @Past(message = " Publication date must be in the past") LocalDate publicationDate,
        @NotBlank(message = "ISBN is required")String isbn)  {



}
