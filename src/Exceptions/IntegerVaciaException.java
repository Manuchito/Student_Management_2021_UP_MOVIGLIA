package Exceptions;

public class IntegerVaciaException extends Exception{
    public IntegerVaciaException(){

    }

    public IntegerVaciaException(String message){
        super(message);
    }

    public IntegerVaciaException(String message, Throwable cause){
        super(message,cause);
    }

    public IntegerVaciaException(Throwable cause){
        super(cause);
    }
}
