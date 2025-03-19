package mapper;

import dto.BookResponse;
import dto.CreateBook;
import entity.Book;
/*
Här centraliserar vi all logik för att
omvandla mellan våra olika dataformat.
Detta gör koden mer underhållbar och testbar.
 */

public class BookMapper {

    private BookMapper() { }

    public static BookResponse toBookResponse(Book book) {
        if (book == null) {
            return null;
        }
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getPublicationDate(),
                book.getIsbn()
        );
    }

    public static Book fromCreateBookDto(CreateBook createBook) {
        if (createBook == null) {
            return null;
        }
        Book book = new Book();
        book.setTitle(createBook.title());
        book.setAuthor(createBook.author());
        book.setDescription(createBook.description());
        book.setPublicationDate(createBook.publicationDate());
        book.setIsbn(createBook.isbn());
        return book;
    }
}
