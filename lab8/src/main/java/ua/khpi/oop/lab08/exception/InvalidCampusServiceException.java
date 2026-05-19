package ua.khpi.oop.lab08.exception;

public class InvalidCampusServiceException extends Exception {

    public InvalidCampusServiceException(String message) {
        super(message);
    }

    public InvalidCampusServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}