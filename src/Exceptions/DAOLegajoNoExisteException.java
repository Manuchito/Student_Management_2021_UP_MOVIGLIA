package Exceptions;



public class DAOLegajoNoExisteException extends Exception{
    public DAOLegajoNoExisteException(){

    }

    public DAOLegajoNoExisteException(String message){
        super(message);
    }

    public DAOLegajoNoExisteException(String message, Throwable cause){
        super(message,cause);
    }

    public DAOLegajoNoExisteException(Throwable cause){
        super(cause);
    }
}
