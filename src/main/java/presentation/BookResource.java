package presentation;

import business.BookService;
import dto.BookResponse;
import dto.CreateBook;
import dto.ResponseDTO;
import dto.UpdateBook;
import entity.Book;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;

import java.util.List;

@Path("books")
@Log
public class BookResource {
    private BookService bookService;
    @Inject
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    public BookResource(){}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDTO getAllBooks() {
        return new ResponseDTO(bookService.getAllBooks());
    }

    @GET
    @Path("{id}")
    @Produces
    public BookResponse getOneBook(@PathParam("id") Long id) {
     return bookService.getBookById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewBook(@Valid @NotNull CreateBook createBook){
        if (createBook == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Book cannot be null")
                    .build();
        }
        BookResponse newBook = bookService.createBook(createBook);
        log.info("Creating new book: " + newBook);
        return Response.status(Response.Status.CREATED)
                .header("Location", "/api/books/" + newBook.id())
                .build();
    }

    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBookFieldByField(@PathParam("id") Long id,
                                           @Valid UpdateBook updateBook){
        bookService.updateBook(updateBook, id);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") Long id, @Valid UpdateBook updateBook){
        bookService.updateBook(updateBook, id);
        return Response.ok().build();
    }

//    @GET
//    @Path("title")

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("id") Long id){
        bookService.deleteBook(id);
        return Response.ok().build();
    }






}
