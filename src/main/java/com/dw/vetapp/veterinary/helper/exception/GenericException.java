package com.dw.vetapp.veterinary.helper.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericException extends RuntimeException{
    private HttpStatus status;
    private String errorMessage;
}
