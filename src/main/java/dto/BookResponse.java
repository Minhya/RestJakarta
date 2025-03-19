package dto;

import entity.Book;

import java.time.LocalDate;

public record BookResponse(
        Long id,
        String title,
        String author,
        String description,
        LocalDate publicationDate,
        String isbn
        ) {
    public static BookResponse map(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublicationDate(), book.getDescription());
    }

}
