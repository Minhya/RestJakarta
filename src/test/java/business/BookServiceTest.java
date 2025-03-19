package business;

import dto.BookResponse;
import dto.CreateBook;
import dto.UpdateBook;
import entity.Book;
import exceptions.NotFound;
import mapper.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import persistence.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book fakeBook;

    @BeforeEach
    public void setUp() {
        fakeBook = new Book("Test Title", "Test Author", "Test Description",
                LocalDate.of(2020, 1, 1), "12345678901");
        fakeBook.setId(1L);
    }

    @Test
    public void getAllBooks() {
        when(bookRepository.findAll()).thenReturn(Stream.of(fakeBook));

        List<BookResponse> books = bookService.getAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals(fakeBook.getTitle(), books.get(0).title());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void getBookByIdIsSuccessful() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(fakeBook));

        BookResponse book = bookService.getBookById(1L);
        assertNotNull(book);
        assertEquals(fakeBook.getTitle(), book.title());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void getBookByIdIsNotFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFound.class,
                () -> {
                    bookService.getBookById(2L);
                });

        String expectedMessage = "Book not found with id: 2";
        assertTrue(exception.getMessage().contains(expectedMessage));
        verify(bookRepository, times(1)).findById(2L);
    }

    @Test
    public void createBook() {
        CreateBook createBook = new CreateBook("New Title", "New Author", "New Description",
                LocalDate.of(2019, 1, 1), "098765432198");

        Book newBook = BookMapper.fromCreateBookDto(createBook);
        newBook.setId(2L);
        when(bookRepository.insert(any(Book.class))).thenReturn(newBook);

        BookResponse book = bookService.createBook(createBook);

        assertNotNull(book);
        assertEquals(2L, book.id());
        assertEquals(newBook.getTitle(), book.title());
        verify(bookRepository, times(1)).insert(any(Book.class));
    }

    @Test
    public void updateBookIsSuccessful() {
        UpdateBook updateBook = new UpdateBook(1L, "Updated Title", null,
                "Updated Description", LocalDate.of(2020, 2, 2), null);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(fakeBook));

        bookService.updateBook(updateBook, 1L);

        assertEquals("Updated Title", fakeBook.getTitle());
        assertEquals("Updated Description", fakeBook.getDescription());
        assertEquals("Test Author", fakeBook.getAuthor());
        assertEquals(LocalDate.of(2020, 2, 2), fakeBook.getPublicationDate());
        verify(bookRepository, times(1)).update(fakeBook);
    }

    @Test
    public void updateBookIsNotFound() {
        UpdateBook updateBook = new UpdateBook(2L, "Updated Title", null,
                "Updated Description", LocalDate.of(2020, 2, 2), null);
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFound.class,
                () -> {
                    bookService.updateBook(updateBook, 2L);
                });

        String expectedMessage = "Book not found with id: 2";
        assertTrue(exception.getMessage().contains(expectedMessage));
        verify(bookRepository, times(1)).findById(2L);

    }

    @Test
    public void deleteBookIsSuccessful() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(fakeBook));
        bookService.deleteBook(1L);
        verify(bookRepository, times(1)).delete(fakeBook);
    }

    @Test
    public void deleteBookIsNotFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFound.class, () -> {
            bookService.deleteBook(2L);
        });
        String expectedMessage = "Book not found with id: 2";
        assertTrue(exception.getMessage().contains(expectedMessage));
        verify(bookRepository, times(1)).findById(2L);
    }

    @Test
    public void getBookByTitleIsSuccessful() {
        when(bookRepository.findAll()).thenReturn(Stream.of(fakeBook));

        List<BookResponse> books = bookService.getBookByTitle("Test Title");
        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals(fakeBook.getTitle(), books.get(0).title());
        verify(bookRepository, times(1)).findAll();

    }

    @Test
    public void getBookByTitleIsNotFound() {
        when(bookRepository.findAll()).thenReturn(Stream.empty());
        List<BookResponse> books = bookService.getBookByTitle("Does-not-exist Title");

        assertNotNull(books);
        assertTrue(books.isEmpty());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void getBooksByAuthorIsSuccessful() {
        when(bookRepository.findAll()).thenReturn(Stream.of(fakeBook));
        List<BookResponse> books = bookService.getBooksByAuthor("Test Author");
        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals(fakeBook.getTitle(), books.get(0).title());
        verify(bookRepository, times(1)).findAll();

    }

    @Test
    public void getBooksByAuthorIsNotFound() {
        when(bookRepository.findAll()).thenReturn(Stream.empty());
        List<BookResponse> books = bookService.getBooksByAuthor("Does-not-exist Author");
        assertNotNull(books);
        assertTrue(books.isEmpty());
        verify(bookRepository, times(1)).findAll();
    }


}