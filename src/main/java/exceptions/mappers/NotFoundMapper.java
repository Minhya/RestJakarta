package exceptions.mappers;

import exceptions.NotFound;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException notFound) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(notFound.getMessage())
                .build();
    }

}
//    @Override
//    public Response toResponse(NotFound notFoundException) {
//        return Response.status(Response.Status.NOT_FOUND)
//                .entity(new ErrorMessage(notFoundException.getMessage()))
//                .build();
//    }

//    public static class ErrorMessage {
//        private String errorMessage;
//
//        public ErrorMessage (String errorMessage) {
//            this.errorMessage = errorMessage;
//        }
//
//        public String getErrorMessage() {
//            return errorMessage;
//        }
//
//    }
