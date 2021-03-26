package model;


import com.example.Course.project.exeption.ErrorInputData;
import com.example.Course.project.model.Card;
import org.junit.Test;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class CardTest {

    String string;

    public void setString(String string) {
        this.string = string;
    }

    @Test
    public void testIsEmptyTrueNull(){
        Card card = new Card();
        assertTrue(card.isEmpty(null));
    }

    @Test
    public void testIsEmpty(){
        Card card = new Card();
        assertTrue(card.isEmpty(""));
    }

    @Test
    public void testIsEmptyR(){
        Card card = new Card();
        assertFalse(card.isEmpty("String"));
    }

    @Test
    public void testCheckNumber1Null() {
        Card card = new Card ();
        setString(null);
        assertThrows(ErrorInputData.class, () -> card.checkNumber1(string));
    }

    @Test
    public void testCheckNumber1Empty() {
        Card card = new Card ();
        setString("");
        assertThrows(ErrorInputData.class, () -> card.checkNumber1(string));
    }

    @Test
    public void testCheckNumber1NotNull() {
        Card card = new Card ();
        setString("22");
        assertTrue(card.checkNumber1(string));
    }

    @Test
    public void testCheckNumber2LessThan() {
        Card card = new Card ();
        setString("2222");
        assertThrows(ErrorInputData.class, () -> card.checkNumber2(string));
    }

    @Test
    public void testCheckNumber2Above() {
        Card card = new Card ();
        setString("112222222222222222");
        assertThrows(ErrorInputData.class, () -> card.checkNumber2(string));
    }

    @Test
    public void testCheckNumber2Eequal() {
        Card card = new Card ();
        setString("2222222222222222");
        assertTrue(card.checkNumber2(string));
    }

    @Test
    public void testParse() {
        Card card = new Card ();
        setString("12/22");
        YearMonth test = card.parse(string);
        assertEquals(test, YearMonth.parse(string, DateTimeFormatter.ofPattern("MM/uu")));
    }

    @Test
    public void testCheckValidTillNull() {
        Card card = new Card ();
        setString(null);
        assertThrows(ErrorInputData.class, () -> card.checkValidTill(string));
    }

    @Test
    public void testCheckValidTillEmpty() {
        Card card = new Card ();
        setString("");
        assertThrows(ErrorInputData.class, () -> card.checkValidTill(string));
    }

    @Test
    public void testCheckValidTillLessThan() {
        Card card = new Card ();
        setString("2222");
        assertThrows(ErrorInputData.class, () -> card.checkValidTill(string));
    }

    @Test
    public void testCheckValidTillAbove() {
        Card card = new Card ();
        setString("112222222222222222");
        assertThrows(ErrorInputData.class, () -> card.checkValidTill(string));
    }
    @Test
    public void testCheckValidTillZone() {
        Card card = new Card ();
        setString("12/20");
        assertThrows(ErrorInputData.class, () -> card.checkValidTill(string));
    }

    @Test
    public void testCheckValidTill() {
        Card card = new Card ();
        setString("12/23");
        assertTrue(card.checkValidTill(string));
    }

    @Test
    public void testCheckCardCVVNull() {
        Card card = new Card ();
        setString(null);
        assertThrows(ErrorInputData.class, () -> card.checkCardCVV(string));
    }

    @Test
    public void testCheckCardCVVEmpty() {
        Card card = new Card ();
        setString("");
        assertThrows(ErrorInputData.class, () -> card.checkCardCVV(string));
    }

    @Test
    public void testCheckCardCVVLessThan() {
        Card card = new Card ();
        setString("55");
        assertThrows(ErrorInputData.class, () -> card.checkCardCVV(string));
    }

    @Test
    public void testCheckCardCVVAbove() {
        Card card = new Card ();
        setString("22222");
        assertThrows(ErrorInputData.class, () -> card.checkCardCVV(string));
    }

    @Test
    public void testCheckCardCVV() {
        Card card = new Card ();
        setString("222");
        assertTrue(card.checkCardCVV(string));
    }

}






//    @Test
//    public void testMethod() {
//
//        String number ="1254425265744";
//        String validTill = "1221";
//        String cardCVV = "125";
//        final Card card = new Card(number,validTill,cardCVV);
//
//        assertThat (number,equalTo(card.getNumber()));
//        assertThat (validTill,equalTo(card.getValidTill()));
//        assertThat (cardCVV,equalTo(card.getCardCVV()));
//    }

