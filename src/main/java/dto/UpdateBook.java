package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record UpdateBook(
        @NotNull(message = "ID required to update")Long id,
        String title,
        String author,
        String description,
        LocalDate publicationDate,
        String isbn
) {



}
