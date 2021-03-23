package com.example.Course.project.exeption;

import org.springframework.http.HttpStatus;

public class ErrorInputData extends RuntimeException{

//    private
    public ErrorInputData(String msg) {
        super(msg);

    }
}
