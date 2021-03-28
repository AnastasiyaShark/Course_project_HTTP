package model;

import com.example.Course.project.model.ConfirmationOfTheOperation;
import com.example.Course.project.model.Operation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


import java.io.IOException;


public class ConfirmationOfTheOperationTest {

    @Test
    public void testDeSerializingWithJsonCreatorConfirmationOfTheOperation() throws IOException {
        String jsonString = "{\"operationId\": \"231\", \"code\": \"rubel\"}";
        ObjectMapper mapper = new ObjectMapper();
        ConfirmationOfTheOperation example = mapper.readValue(jsonString, ConfirmationOfTheOperation.class);
        Operation operation = example.getOperationId();
        assertThat(operation.getOperationId(), equalTo("231"));
        assertThat(example.getCode(), equalTo("rubel"));
    }
}
