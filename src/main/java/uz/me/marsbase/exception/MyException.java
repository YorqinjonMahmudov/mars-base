package uz.me.marsbase.exception;

public class MyException extends RuntimeException {

    public MyException() {

    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

}

