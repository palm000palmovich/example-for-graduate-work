package ru.skypro.homework.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Not authenticated");
    }
}
