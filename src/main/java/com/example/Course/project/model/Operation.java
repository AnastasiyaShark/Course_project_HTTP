package com.example.Course.project.model;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Random;

public class Operation {

    private String operationId;


    public Operation(String operationId) {
        this.operationId = operationId;
    }

    public static String generationСode() {
        Random rand = new Random();
        int a = rand.nextInt();
        String code= String.valueOf(a);
        return code;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return  operationId;
    }
}
