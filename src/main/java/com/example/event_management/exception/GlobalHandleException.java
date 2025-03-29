package com.example.event_management.exception;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalHandleException {
    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFoundException(UserNotFoundException e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        detail.setType(URI.create("about:blank"));
        detail.setTitle("Bad request");
        detail.setProperty("timestamp", LocalDateTime.now());
        return detail;
    }

    //-- validation request body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        // catch message
        HashMap<String, String> errors = new HashMap<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        detail.setType(URI.create("about:blank"));
        detail.setTitle("Bad request");
        detail.setProperty("timestamp", LocalDateTime.now());
        detail.setProperty("errors", errors);

        return detail;

    }

    // validation path variable
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(HandlerMethodValidationException e) {

        // catch message

        HashMap<String, String> errors = new HashMap<>();

        for (MessageSourceResolvable pathErr : e.getAllErrors()) {
            for (String err : pathErr.getCodes()) {
                if (err.contains("Positive.attendeeId")) {
                    errors.put("attendeeId", pathErr.getDefaultMessage());
                }
                if (err.contains("Positive.offset")) {
                    errors.put("offset", pathErr.getDefaultMessage());
                }
                if (err.contains("Positive.limit")) {
                    errors.put("limit", pathErr.getDefaultMessage());
                }
                if (err.contains("Positive.venueId")) {
                    errors.put("venueId", pathErr.getDefaultMessage());
                }
                if (err.contains("Positive.eventId")) {
                    errors.put("eventId", pathErr.getDefaultMessage());
                }
                if (err.contains("Positive.eventId")) {
                    errors.put("eventId", pathErr.getDefaultMessage());
                }
                if (err.contains("NotBlank.attendee")) {
                    errors.put("attendeeRequest", "must not be blank");
                }
                if (err.contains("Email.email")) {
                    errors.put("email", pathErr.getDefaultMessage());
                }
                if (err.contains("NotBlank.event")) {
                    errors.put("eventRequest", "must not be blank");
                }
                if (err.contains("NotBlank.venue") || err.contains("NotBlank.location")) {
                    errors.put("eventRequest", "must not be blank");
                }


            }
        }

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        detail.setType(URI.create("about:blank"));
        detail.setTitle("Bad request");
        detail.setProperty("timestamp", LocalDateTime.now());
        detail.setProperty("errors", errors);
        return detail;
    }


}
