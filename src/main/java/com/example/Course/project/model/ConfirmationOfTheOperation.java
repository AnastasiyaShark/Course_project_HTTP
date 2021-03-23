package com.example.Course.project.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ConfirmationOfTheOperation {

    private Operation operationId;
    private String code;

    @JsonCreator
    public ConfirmationOfTheOperation(@JsonProperty("operationId")Operation operationId,
                                      @JsonProperty("code")String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public Operation getOperationId() {
        return operationId;
    }

    public String getCode() {
        return code;
    }
}
