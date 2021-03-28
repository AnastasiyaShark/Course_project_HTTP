package com.example.Course.project.model;


import com.example.Course.project.exeption.ErrorInputData;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


public class Card {

    private String number;
    private String validTill;
    private String cardCVV;
    private String forCardTo = "not indicated";


    public Card(String number, String validTill, String cardCVV) {
        if (checkNumber1(number) && checkNumber2(number)) {
            this.number = number;
        }
        if (checkValidTill(validTill)) {
            this.validTill = validTill;
        }
        if (checkCardCVV(cardCVV)) {
            this.cardCVV = cardCVV;
        }

    }

    public Card() {
    }

    public Card(String number) {
        if (checkNumber1(number) && checkNumber2(number)) {
            this.number = number;
        }
        this.validTill = forCardTo;
        this.cardCVV = forCardTo;

    }

    public boolean checkNumber1(String number) {
        //если пусто
        if (isEmpty(number)) {
            throw new ErrorInputData("The field \"Card number\" is not filled.");
        }
        return true;
    }

    public boolean checkNumber2(String number) {
        // минимум 16 знаков
        if (number.length() != 16) {
            throw new ErrorInputData("The field \"Card number\" contains the wrong number of characters.");
        }
        return true;
    }

    public boolean checkValidTill(String validTill) {
        //если пусто
        if (isEmpty(validTill)) {
            throw new ErrorInputData("The field \"MM / YY\" is not filled.");
        }
        //минимум 4 знака
        if (validTill.length() != 5) {
            throw new ErrorInputData("The field \"MM / YY\" contains an invalid number of characters.");
        }
        YearMonth lastValidMonth = parse(validTill);
        if (YearMonth.now(ZoneOffset.UTC).isAfter(lastValidMonth)) {
            throw new ErrorInputData("The date of validity of the sender's card has expired.");
        }
        //месяц не может быть ниже 1
        //месяц не может быть выше 12
        int month = lastValidMonth.getMonthValue();
        if (month < 1 || month > 12) {
            throw new ErrorInputData("The field \"MM / YY\" contains a month less than 1 or more than 12.");
        }
        return true;
    }


    //parse
    public YearMonth parse(String validTill) {
        try {
            DateTimeFormatter ccMonthFormatter = DateTimeFormatter.ofPattern("MM/uu");
            //получает экземпляр YearMonth из текстовой строки с помощью специального средства форматирования.
            return YearMonth.parse(validTill, ccMonthFormatter);
        } catch (DateTimeException ex) {
            throw new ErrorInputData("Failed to parse.");
        }

    }

    public boolean checkCardCVV(String cardCVV) {
        //если пусто
        if (isEmpty(cardCVV)) {
            throw new ErrorInputData("The field \"CVV\" is not filled.");
        }
        // минимум 3 знака
        if (cardCVV.length() != 3) {
            throw new ErrorInputData("The field \"CVV\"is contains an invalid number of characters.");
        }
        return true;
    }

    public boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public String getNumber() {
        return number;
    }

    public String getValidTill() {
        return validTill;
    }

    public String getCardCVV() {
        return cardCVV;
    }


    @Override
    public String toString() {
        if (validTill.equals(forCardTo) && cardCVV.equals(forCardTo)) {
            return "Card{" +
                    "number='" + number + '\'' +
                    '}';
        }
        return "Card{" +
                "number='" + number + '\'' +
                ", validTill='" + validTill + '\'' +
                ", cardCVV='" + cardCVV + '\'' +
                '}';

    }
}





