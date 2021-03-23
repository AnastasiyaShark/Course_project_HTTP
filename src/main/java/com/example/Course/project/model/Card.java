package com.example.Course.project.model;


import com.example.Course.project.exeption.ErrorInputData;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Card {

    private String number;
    private String validTill;
    private String cardCVV;
    private String forCardTo = "not indicated";


    public Card(String number,String validTill,String cardCVV) {
        if (checkNumber(number)) {
            this.number = number;
        }
        if (checkValidTill(validTill)) {
            this.validTill = validTill;
        }
        if (checkCardCVV(cardCVV)) {
            this.cardCVV = cardCVV;
        }

    }


    public Card(String number) {
        if (checkNumber(number)) {
            this.number = number;
        }
        this.validTill = forCardTo;
        this.cardCVV = forCardTo;

    }

    private boolean checkNumber(String number) {
        //если пусто
        if (isEmpty(number)) {
            throw new ErrorInputData("Card number is empty");
        }
        // минимум 16 знаков
        if (number.length() != 16) {
            throw new ErrorInputData("Card number is incorrect");
        }
        return true;
    }

    private boolean checkValidTill(String validTill) {
        //если пусто
        if (isEmpty(validTill)) {
            throw new ErrorInputData("Card expiration date is empty");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
        simpleDateFormat.setLenient(false);
        try {
        Date expiry = simpleDateFormat.parse(validTill);
        boolean expired = expiry.before(new Date());
        if (expired) {
            System.out.println("no");
            throw new ErrorInputData("Card expiration date is incorrect");
        }
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        //дата не может быть ниже текущей
//        DateTimeFormatter ccMonthFormatter = DateTimeFormatter.ofPattern("MM/uu");
//        //получает экземпляр YearMonth из текстовой строки с помощью специального средства форматирования.
//        YearMonth lastValidMonth = YearMonth.parse(validTill, ccMonthFormatter);
//        if (YearMonth.now(ZoneOffset.UTC).isAfter(lastValidMonth)) {
//            System.out.println("no");
//            throw new ErrorInputData("Card expiration date is incorrect");
//        }
        //минимум 4 знака
        if (validTill.length() < 4) {
            throw new ErrorInputData("Card expiration date is incorrect");
        }
        //месяц не может быть ниже 1
        if (validTill.startsWith("00")) {
            throw new ErrorInputData("Card expiration date is incorrect");
        }
        //месяц не может быть выше 12
        int validMonth = Integer.parseInt(validTill.substring(0, 1));
        if (validMonth > 12) {

            throw new ErrorInputData("Card expiration date is incorrect");
        }
        return true;
    }

    private boolean checkCardCVV(String cardCVV) {
        //если пусто
        if (isEmpty(cardCVV)) {
            throw new ErrorInputData("Cards CVV is empty");
        }
        // минимум 3 знака
        if (cardCVV.length() != 3) {
            throw new ErrorInputData("Cards CVV is incorrect");
        }
        return true;
    }

    private boolean isEmpty(String str) {
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
}





