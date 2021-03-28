package model;

import com.example.Course.project.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TransferTest {
    String cardFromNumber = "2222222222222222";
    String cardFromValidTill = "12/22";
    String cardFromCVV = "222";
    String cardToNumber = "3333333333333333";
    int value = 2543;
    String currency = "rubel";


    @Test
    public void testDeSerializingWithJsonCreatorTransfer() throws IOException {

        String jsonString = String.format("{\"cardFromNumber\": \"%s\", \"cardFromValidTill\": \"%s\", \"cardFromCVV\": \"%s\", " +
                        "\"cardToNumber\":  \"%s\", \"amount\": {\"value\": \"%d\", \"currency\": \"%s\" }}",
                cardFromNumber, cardFromValidTill, cardFromCVV, cardToNumber, value, currency);

        ObjectMapper mapper = new ObjectMapper();
        Transfer transfer = mapper.readValue(jsonString, Transfer.class);

        assertThat(transfer.getCardFrom().getNumber(), equalTo(cardFromNumber));
        assertThat(transfer.getCardFrom().getValidTill(), equalTo(cardFromValidTill));
        assertThat(transfer.getCardFrom().getCardCVV(), equalTo(cardFromCVV));
        assertThat(transfer.getCardTo().getNumber(), equalTo(cardToNumber));
        assertThat(transfer.getAmount().getValue(), equalTo(value));
        assertThat((transfer.getAmount().getCurrency()), equalTo(currency));
    }

}
