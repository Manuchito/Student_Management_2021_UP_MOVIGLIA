package Service.Exceptions;

public class ClaveNoExisteException extends Exception {

    public ClaveNoExisteException(){

    }
    public ClaveNoExisteException(String message){
        super(message);
    }

    public ClaveNoExisteException(String message, Throwable cause){
        super(message, cause);
    }

    public ClaveNoExisteException(Throwable cause){
        super(cause);
    }
}
