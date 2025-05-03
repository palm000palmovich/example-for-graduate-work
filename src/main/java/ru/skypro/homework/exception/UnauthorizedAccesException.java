package ru.skypro.homework.exception;

public class UnauthorizedAccesException extends RuntimeException {
    public UnauthorizedAccesException() {
        super("Unauthorized access");
    }
}
