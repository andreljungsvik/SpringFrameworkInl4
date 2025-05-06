package se.yrgo.dataaccess;

public class RecordNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(){
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}