package ru.skypro.homework.exception;

public class ForbiddenAccesException extends RuntimeException {
    public ForbiddenAccesException() {
        super("Forbidden access");
    }
}
