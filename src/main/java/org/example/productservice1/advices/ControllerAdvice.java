package org.example.productservice1.advices;

import org.example.productservice1.dtos.ErrorDto;
import org.example.productservice1.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());

        ResponseEntity<ErrorDto> res = new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);

        return res;
    }
}
