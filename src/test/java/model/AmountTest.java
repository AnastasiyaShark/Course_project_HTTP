package model;

import com.example.Course.project.exeption.ErrorInputData;
import com.example.Course.project.model.Amount;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class AmountTest {

    Integer value;

    public void setValue(Integer value) {
        this.value = value;
    }

    @Test
    public void testDeSerializingWithJsonCreatorAmount() throws IOException {
        String jsonString = "{\"value\": 231, \"currency\": \"rubel\"}";
        ObjectMapper mapper = new ObjectMapper();
        Amount amount = mapper.readValue(jsonString, Amount.class);
        assertThat(amount.getValue(), equalTo(231));
        assertThat(amount.getCurrency(), equalTo("rubel"));
    }

    @Test
    public void testIsEmpty() {
        Amount amount = new Amount();
        assertFalse(amount.isEmpty(4));
    }

    @Test
    public void testIsEmptyNull() {
        Amount amount = new Amount();
        assertTrue(amount.isEmpty(null));
    }

    @Test
    public void testCheckValueNullException() {
        Amount amount = new Amount();
        setValue(null);
        assertThrows(ErrorInputData.class, () -> amount.checkValue(value));
    }

    @Test
    public void testCheckValueNotNullException() {
        Amount amount = new Amount();
        setValue(54564);
        assertTrue(amount.checkValue(value));
    }

    @Test
    public void testCheckValueLessThanZero() {
        Amount amount = new Amount();
        setValue(-2);
        assertThrows(ErrorInputData.class, () -> amount.checkValue(value));
    }

    @Test
    public void testCheckValueEqualToZero() {
        Amount amount = new Amount();
        setValue(0);
        assertThrows(ErrorInputData.class, () -> amount.checkValue(value));
    }

    @Test
    public void testCheckValueAboveZero() {
        Amount amount = new Amount();
        setValue(521465);
        assertTrue(amount.checkValue(value));
    }


}
