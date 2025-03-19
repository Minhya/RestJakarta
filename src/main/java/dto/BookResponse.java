package dto;

import entity.Book;

import java.time.LocalDate;

public record BookResponse(
        Long id,
        String title,
        String author,
        String isbn,
        LocalDate publicationDate,
        String description) {
    public BookResponse(Book book) {
        this(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublicationDate(), book.getDescription());
    }

    public static BookResponse map(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublicationDate(), book.getDescription());
    }

}


// kolla igenom
//    public BookResponse(Book book){
//        this.id = book.getId();
//        this.title = book.getTitle();
//        this.author = book.getAuthor();
//    }
//
//    public static BookResponse map(Book book){
//        return new BookResponse(book);
//    }





//public BookResponse() {
//
//}
//public BookResponse(Long id, String title, String author, String isbn, LocalDate publicationDate, String description) {
//    this.title = title;
//    this.author = author;
//    this.isbn = isbn;
//    this.publicationDate = publicationDate;
//    this.description = description;
//
//}
//
//// kolla igenom denna igen
//public BookResponse(Book book) {
//}
//
//public Long getId() {
//    return id;
//}
//public void setId(Long id) {
//    this.id = id;
//}
//public String getTitle() {
//    return title;
//}
//public void setTitle(String title) {
//    this.title = title;
//}
//public String getAuthor() {
//    return author;
//}
//public void setAuthor(String author) {
//    this.author = author;
//}
//public String getDescription() {
//    return description;
//}
//public void setDescription(String description) {
//    this.description = description;
//}
//public LocalDate getPublicationDate() {
//    return publicationDate;
//}
//public void setPublicationDate(LocalDate publicationDate) {
//    this.publicationDate = publicationDate;
//}
//public String getIsbn() {
//    return isbn;
//}
//public void setIsbn(String isbn) {
//    this.isbn = isbn;
//}