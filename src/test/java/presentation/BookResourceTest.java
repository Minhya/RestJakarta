package presentation;

import business.BookService;
import dto.BookResponse;
import dto.CreateBook;
import dto.ResponseDTO;
import dto.UpdateBook;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookResourceTest {

    @Mock
    private BookService bookService;
    @InjectMocks
    private BookResource bookResource;

    private BookResponse fakeResponse;

    @BeforeEach
    void setUp() {
        fakeResponse = new BookResponse(1L, "Test Title", "Test Author",
                "Test Description", LocalDate.of(2020, 1, 1), "1234567901");
    }

    @Test
    public void getAllBooks() {
        when(bookService.getAllBooks()).thenReturn(List.of(fakeResponse));

        ResponseDTO responseDTO = bookResource.getAllBooks();

        assertNotNull(responseDTO);
        assertEquals(1, responseDTO.data().size());
        assertEquals("Test Title", responseDTO.data().getFirst().title());
        verify(bookService, times(1)).getAllBooks();

    }

    @Test
    public void getOneBook() {
        when(bookService.getBookById(1L)).thenReturn(fakeResponse);

        BookResponse bookResponse = bookResource.getOneBook(1L);

        assertNotNull(bookResponse);
        assertEquals("Test Title", bookResponse.title());
        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    public void createBook() {
        CreateBook createBook = new CreateBook("New Title", "New Author", "New Description",
                LocalDate.of(2019, 1, 1), "098765432198");

        BookResponse bookResponse = new BookResponse(2L, "New Title", "New Author", "New Description",
                LocalDate.of(2019, 1, 1), "098765432198");

        when(bookService.createBook(any(CreateBook.class))).thenReturn(bookResponse);

        Response response = bookResource.createNewBook(createBook);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertTrue(response.getHeaderString("Location").contains("2"));
        verify(bookService, times(1)).createBook(any(CreateBook.class));


    }

    @Test
    public void updateBook() {
        UpdateBook updateBook = new UpdateBook(1L, "Updated title", null,
                "Updated description",
                LocalDate.of(2020, 2, 2), null);

        doNothing().when(bookService).updateBook(any(UpdateBook.class), eq(1L));
        Response patchResponse = bookResource.updateBookFieldByField(1L, updateBook);
        Response putResponse = bookResource.updateBook(1L, updateBook);

        assertEquals(Response.Status.OK.getStatusCode(), patchResponse.getStatus());
        assertEquals(Response.Status.OK.getStatusCode(), putResponse.getStatus());
        verify(bookService, times(2)).updateBook(any(UpdateBook.class), eq(1L));
    }

    @Test
    public void deleteBook() {
        doNothing().when(bookService).deleteBook(1L);
        Response deleteResponse = bookResource.deleteBook(1L);
        assertEquals(Response.Status.OK.getStatusCode(), deleteResponse.getStatus());
        verify(bookService, times(1)).deleteBook(1L);
    }

}