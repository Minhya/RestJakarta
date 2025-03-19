package persistence;

import entity.Book;
import jakarta.data.repository.*;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Find
    Optional<Book> findByTitle(String title);

    @Query("select b from Book b where b.id = :id")
    Optional<Book> findById(@Param("id") Long id);

    @Query("select b from Book b where b.author = :author")
    Optional<Book> findByAuthor(@Param("author") String author);


}
