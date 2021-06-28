package project.exceptions;

public class DniNoValidoException extends Exception{

    public DniNoValidoException() {
        super();
    }

    public DniNoValidoException(String mensaje){
        super(mensaje);
    }
}
