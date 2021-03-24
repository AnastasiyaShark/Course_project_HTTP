package com.example.Course.project.model;

import java.util.Random;

public class Operation {

    private String operationId;


    public Operation(String operationId) {
        this.operationId = operationId;
    }

    public static String generationСode() {
        Random rand = new Random();
        int maxValueA = 9632564;
        int a = rand.nextInt(maxValueA);
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
