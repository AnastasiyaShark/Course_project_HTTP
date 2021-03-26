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

    public Amount() {
    }

    public boolean checkValue(Integer  value) {
        //если пусто
        if (isEmpty(value)) {
            throw new ErrorInputData("The field \"Transfer amount\" is not filled.");
        }
        // не может быть равное или меньше 0
        if (value <= 0) {
            throw new ErrorInputData("The \"Transfer Amount\" field contains a value equal to 0 or less than 0.");
        }
        return true;
    }

    public boolean isEmpty(Integer value) {
        return value == null;
    }

    public int getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}


