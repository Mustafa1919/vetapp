package com.dw.vetapp.veterinary.helper.exception;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<?> handlerException(GenericException ex){
        Map<String, Object> errors = new HashMap<>();
        errors.put("message",ex.getErrorMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(),err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<String> handle(RequestNotPermitted exception){
        return new ResponseEntity<>("Çok Fazla Deneme Yaptınız", HttpStatus.TOO_MANY_REQUESTS);
    }
}
