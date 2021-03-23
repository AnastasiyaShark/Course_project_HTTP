package com.example.Course.project.model;

import com.example.Course.project.exeption.ErrorInputData;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Amount {

    private int value;
    private String currency;

    @JsonCreator
    public Amount(@JsonProperty ("value") Integer value,
                  @JsonProperty ("currency")String currency ) {
        if (checkValue(value)){
            this.value = value;
        }
        this.currency = currency;
    }

    private boolean checkValue(Integer  value) {
        //если пусто
        if (isEmpty(value)) {
            throw new ErrorInputData("Value is empty");
        }
        // не может быть равное или меньше 0
        if (value <= 0) {
            throw new ErrorInputData("Value is incorrect");
        }
        return true;
    }

    private boolean isEmpty(Integer value) {
        return value == null;
    }

    public int getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }
}


