package dto;

import jakarta.validation.constraints.NotNull;

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
