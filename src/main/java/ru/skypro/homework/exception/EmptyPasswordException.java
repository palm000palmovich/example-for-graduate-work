package ru.skypro.homework.exception;

public class EmptyPasswordException extends RuntimeException {
    public EmptyPasswordException() {
        super("An empty password was entered. New password is required");
    }
}