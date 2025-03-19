package entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Objects;

/*
Här använder vi JPA för att mappa entiteten
till databastabellen “books”. Validerings-annotationer
säkerställer att vi har rätt format på fälten
(t.ex. ISBN och att datumet ligger i det förflutna).
Unik constraint säkerställer att kombinationen av
title och author är unik.
*/

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is needed.")
    private String title;

    @NotBlank(message = "Author is needed.")
    private String author;

    @NotBlank(message = "Description is needed.")
    private String description;

    @Past(message = "Publishing date must be in the past.")
    private LocalDate publicationDate;

    @NotBlank(message = "ISBN is needed.")
    private String isbn;


    public Book(){
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", publicationDate=" + publicationDate +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    public Book(String title, String author, String description, LocalDate publicationDate, String isbn){
        this.title = title;
        this.author = author;
        this.description = description;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


}
