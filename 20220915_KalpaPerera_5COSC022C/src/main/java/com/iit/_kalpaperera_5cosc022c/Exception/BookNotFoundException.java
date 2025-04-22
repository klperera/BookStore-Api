package com.iit._kalpaperera_5cosc022c.Exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message){
        super(message);
    }
}
