package se.yrgo.services.customers;

public class CustomerNotFoundException extends Exception {
    // this is just to stop the annoying warning in Eclipse.
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(){
        super();
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
