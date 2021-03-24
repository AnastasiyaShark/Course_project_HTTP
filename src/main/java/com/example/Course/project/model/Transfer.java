package com.example.Course.project.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Transfer {


    private Card cardFrom;
    private Card cardTo;
    private Amount amount;
    private Operation operationId;
    @JsonCreator
    public Transfer(@JsonProperty("cardFromNumber") String number1,
                    @JsonProperty("cardFromValidTill") String validTill,
                    @JsonProperty("cardFromCVV") String cardCVV,
                    @JsonProperty("cardToNumber") String number2,
                    @JsonProperty("amount") Amount amount) {

        this.cardFrom = new Card(number1, validTill, cardCVV);
        this.cardTo = new Card(number2);
        this.amount = amount;
        this.operationId = new Operation(Operation.generationСode());

    }

    public Transfer() {
    }

    public Card getCardFrom() {
        return cardFrom;
    }

    public Card getCardTo() {
        return cardTo;
    }

    public Amount getAmount() {
        return amount;
    }

    public Operation getOperationId() {
        return operationId;
    }
}
