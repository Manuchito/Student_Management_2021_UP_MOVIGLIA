package Exceptions;



public class AlumnoNoExiste extends Exception{
    public AlumnoNoExiste(){

    }

    public AlumnoNoExiste(String message){
        super(message);
    }

    public AlumnoNoExiste(String message, Throwable cause){
        super(message,cause);
    }

    public AlumnoNoExiste(Throwable cause){
        super(cause);
    }
}
