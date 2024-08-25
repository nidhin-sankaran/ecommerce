package com.nidhin.personal.productservice.exceptions;

public class InvalidProductIdException extends  Exception {
    public  InvalidProductIdException(){

    }
    public InvalidProductIdException(String message) {
        super(message);
    }
}
