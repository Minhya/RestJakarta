package persistence;

import entity.Book;
import jakarta.data.repository.*;
//import jakarta.data.repository.CrudRepository;
import java.time.LocalDate;
import java.util.Optional;

/*Detta interface utökar flera repository-gränssnitt
för att få stöd för grundläggande CRUD, paginering
och sortering. Metoderna för filtrering
implementeras automatiskt utifrån
namngivningskonventioner.*/

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Find
    Optional<Book> findByTitle(String title);

    @Query("select b from Book b where b.id = :id")
    Optional<Book> findById(@Param("id") Long id);

    @Query("select b from Book b where b.author = :author")
    Optional<Book> findByAuthor(@Param("author") String author);

}
//    Optional<Book> findByPublicationDateAfter(LocalDate localDate);