package exceptions;

public class NotFound extends RuntimeException{
    public NotFound(String message){
        super(message);
    }
}
