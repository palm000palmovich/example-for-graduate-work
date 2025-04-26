package ru.skypro.homework.exception;

public class PasswordEqualsException extends RuntimeException{
    public PasswordEqualsException() {
        super("You must enter a new password");
    }
}
