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



/*Vi skapar ett service-gränssnitt och en
implementation som hanterar
affärslogiken och använder repository samt mappern.

Service-lagret hanterar all affärslogik, såsom validering,
datakonvertering (via mapper) och anrop till repository.
Metoderna är annoterade med @Transactional där det
behövs för att säkerställa datakonsekvens.*/

@ApplicationScoped
public class BookService {

    private BookRepository bookRepository;

    // Default no-args constructor for CDI proxying
    protected BookService() {
        // This constructor is required by CDI for proxying.
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
        bookRepository.deleteById(id);
    }
}

//private final BookRepository bookRepository;
//
//@Inject
//public BookService(BookRepository bookRepository) {
//    this.bookRepository = bookRepository;
//}
//
//public BookService(){
//    this.bookRepository = null;
//}
//
//public List<BookResponse> getAllBooks() {
//    LocalDate filterBookDateAfter = LocalDate.of(2000,1,1);
//    return bookRepository.findByPublicationDateAfter(filterBookDateAfter)
//            .stream()
//            .map(BookResponse::new)
//            .filter(Objects::nonNull)
//            .toList();
//}
//public BookResponse getBookById(int id) {
//    return bookRepository.findById(id)
//            .map(BookResponse::new)
//            .orElseThrow(() -> new NotFoundException("Book with id" + id + "not found"));
//}
//public Book createBook(CreateBook createBook) {
//    Book newBook = map(createBook);
//    newBook = bookRepository.insert(newBook);
//    return newBook;
//}
//
//public void updateBook(UpdateBook updateBook, Long id) {
//    Book oldBook = bookRepository.findById(id)
//            .orElseThrow(() -> new NotFoundException("Book with id" + id + "not found"));
//
//    if (oldBook.getTitle() != null) {
//        oldBook.setTitle(updateBook.getTitle());
//    }
//    if (oldBook.getPublicationDate() != null) {
//        oldBook.setPublicationDate(updateBook.getPublicationDate());
//    }
//    bookRepository.update(oldBook);
//}


//    List<BookResponse> getAllBooks();
//
//    BookResponse getBookById(Long id);
//
//    BookResponse createBook(CreateBook createBook);
//
//    BookResponse updateBook(UpdateBook updateBook);
//
//    void deleteBook(Long id);
//
//    List<BookResponse> filterBooks(String author, String title);







//private BookRepository bookRepository;
//
//@Inject
//public BookService(BookRepository bookRepository) {
//    this.bookRepository = bookRepository;
//}
//
//public BookService(){
//
//}
//
//public List<BookResponse> getAllBooks() {
//    return bookRepository.findAll()
//            .map(BookResponse::new)
//            .filter(Objects::nonNull).toList();
//}
//
//public BookResponse getBookById(Long id) {
//    return bookRepository.findById(id)
//            .map(BookResponse::new)
//            .orElseThrow(()-> new NotFoundException("Book with" + id +"id not found"));
//}
//
//public Book createBook(CreateBook createBook) {
//    var newBook = map(createBook);
//    newBook = bookRepository.insert(newBook);
//    return newBook;
//
//}
////change notfound later
//public void updateBook(UpdateBook updateBook, Long id) {
//    var oldBook = bookRepository.findById(id)
//            .orElseThrow(()->
//                    new NotFoundException("Book with" + id +"id not found"));
//    if(updateBook.title() != null)
//}
