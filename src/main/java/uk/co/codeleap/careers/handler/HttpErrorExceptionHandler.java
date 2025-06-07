package uk.co.codeleap.careers.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uk.co.codeleap.careers.exceptions.BadRequestException;
import uk.co.codeleap.careers.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class HttpErrorExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> notFound(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());


    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> BadRequest(BadRequestException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }



}
