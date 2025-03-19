package business;

import dto.BookResponse;
import dto.CreateBook;
import dto.UpdateBook;
import entity.Book;
import exceptions.NotFound;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.BookMapper;
import persistence.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookService {

    private BookRepository bookRepository;

    protected BookService() {
    }

    @Inject
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .map(BookMapper::toBookResponse)
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFound("Book not found with id: " + id));
        return BookMapper.toBookResponse(book);
    }

    public List<BookResponse> getBookByTitle(String title) {
        return bookRepository.findAll()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .map(BookMapper::toBookResponse)
                .toList();
    }

    public List<BookResponse> getBooksByAuthor(String author) {
        return bookRepository.findAll()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .map(BookMapper::toBookResponse)
                .toList();
    }

    @Transactional
    public BookResponse createBook(CreateBook createBook) {
        Book book = BookMapper.fromCreateBookDto(createBook);
        book = bookRepository.insert(book);
        return BookMapper.toBookResponse(book);
    }

    @Transactional
    public void updateBook(UpdateBook updateBook, Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFound("Book not found with id: " + id));
        if (updateBook.title() != null) {
            book.setTitle(updateBook.title());
        }
        if (updateBook.author() != null) {
            book.setAuthor(updateBook.author());
        }
        if (updateBook.description() != null) {
            book.setDescription(updateBook.description());
        }
        if (updateBook.publicationDate() != null) {
            book.setPublicationDate(updateBook.publicationDate());
        }
        if (updateBook.isbn() != null) {
            book.setIsbn(updateBook.isbn());
        }
        bookRepository.update(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFound("Book not found with id: " + id));
        bookRepository.delete(book);
    }


}
