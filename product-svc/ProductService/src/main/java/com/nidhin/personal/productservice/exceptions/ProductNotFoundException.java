package com.nidhin.personal.productservice.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
