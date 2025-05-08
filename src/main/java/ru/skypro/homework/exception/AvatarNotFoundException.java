package ru.skypro.homework.exception;

import ru.skypro.homework.service.impl.AvatarServiceImpl;

public class AvatarNotFoundException extends RuntimeException{
    public AvatarNotFoundException(){
        super("This avatar is not found!");
    }
}
