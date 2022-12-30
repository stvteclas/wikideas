package com.idforideas.wikideas.exception;

import com.idforideas.wikideas.dto.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({WikiException.class})
    public ResponseEntity<MessageDTO> wikiExceptionHandler(WikiException ex) {
        MessageDTO message = MessageDTO.builder()
                .message(ex.getMessage())
                .build();
//WebRequest request
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
