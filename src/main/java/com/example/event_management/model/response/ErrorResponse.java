package com.example.event_management.model.response;

import org.springframework.http.HttpStatus;

public class ErrorResponse <T>{
    private String type;
    private String title;
    private HttpStatus status;
    
}
