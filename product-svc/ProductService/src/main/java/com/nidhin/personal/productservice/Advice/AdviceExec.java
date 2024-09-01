package com.nidhin.personal.productservice.Advice;

import com.nidhin.personal.productservice.dto.ErrorDTO;
import com.nidhin.personal.productservice.exceptions.InvalidProductIdException;
import com.nidhin.personal.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceExec {
    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<?> handleInvalidProductIdException(InvalidProductIdException ex) {
        String message = ex.getMessage();
        String code = "101";
        return new ResponseEntity<ErrorDTO>(new ErrorDTO(message, code), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex) {
        String message = ex.getMessage();
        String code = "101";
        return new ResponseEntity<ErrorDTO>(new ErrorDTO(message, code), HttpStatus.BAD_GATEWAY);
    }
}
