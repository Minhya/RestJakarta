package mapper;

import dto.BookResponse;
import dto.CreateBook;
import entity.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookMapperTest {

    @Test
    public void BookResponse() {
        Book book = new Book("Test Title", "Test Author", "Test Description", LocalDate.of(2020, 1, 1),
                "12345678901");
        book.setId(10L);

        BookResponse bookResponse = BookMapper.toBookResponse(book);

        assertNotNull(bookResponse);
        assertEquals(10L, bookResponse.id());
        assertEquals("Test Title", bookResponse.title());
        assertEquals("Test Author", bookResponse.author());
        assertEquals("Test Description", bookResponse.description());
        assertEquals(LocalDate.of(2020, 1, 1), bookResponse.publicationDate());
    }

    @Test
    public void fromCreateBookDTO() {
        CreateBook createBook = new CreateBook("New Title", "New Author",
                "New Description", LocalDate.of(2020, 1, 1), "98765432109");

        Book book = BookMapper.fromCreateBookDto(createBook);
        assertNotNull(book);
        assertEquals("New Title", book.getTitle());
        assertEquals("New Author", book.getAuthor());
        assertEquals("New Description", book.getDescription());
        assertEquals(LocalDate.of(2020, 1, 1), book.getPublicationDate());
        assertEquals("98765432109", book.getIsbn());
    }


}