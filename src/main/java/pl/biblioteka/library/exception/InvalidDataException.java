package pl.biblioteka.library.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String msg) {
        super(msg);
    }
}
