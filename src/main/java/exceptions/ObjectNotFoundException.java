package exceptions;

public class ObjectNotFoundException extends Exception{
    public ObjectNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
