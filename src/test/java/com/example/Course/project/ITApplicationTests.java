package com.example.Course.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.testcontainers.containers.GenericContainer;
import org.junit.jupiter.api.Test;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.nio.file.Path;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ITApplicationTests {
    private static int port = 8080;
    private static String transfer = "/transfer";
    private static String confirmOperation = "/confirmOperation";

    private final static Path RESOURCE_PATH = Path.of(System.getProperty("user.dir"));

    String cardFromNumber = "2222222222222222";
    String cardFromValidTill = "12/22";
    String cardFromCVV = "222";
    String cardToNumber = "3333333333333333";
    int value = 2543;
    String currency = "rubel";
    String operationId = "23156632";
    String code = "0000";


    @Autowired
    private TestRestTemplate restTemplate;
    public static GenericContainer<?> courseApp = new GenericContainer<>(new ImageFromDockerfile()
            .withFileFromPath(".", RESOURCE_PATH)).
            withExposedPorts(port);

    @BeforeAll
    public static void setUp() {
        courseApp.start();
    }

    @Test
    void testSave() {
        String jsonString = String.format("{\"cardFromNumber\": \"%s\", \"cardFromValidTill\": \"%s\", \"cardFromCVV\": \"%s\", " +
                        "\"cardToNumber\":  \"%s\", \"amount\": {\"value\": \"%d\", \"currency\": \"%s\" }}",
                cardFromNumber, cardFromValidTill, cardFromCVV, cardToNumber, value, currency);
        final String baseUrl = String.format("http://localhost:%d%s", courseApp.getMappedPort(port), transfer);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);
        assertEquals(response.getStatusCode(), (HttpStatus.OK));
        String operationId = response.getBody();

        Assertions.assertNotNull(operationId);
        Assertions.assertNotEquals(operationId, "");

    }

    @Test
    void testConfirm() {
        String jsonString = String.format("{\"operationId\": \"%s\", \"code\": \"%s\"}",
                operationId, code);
        final String baseUrl = String.format("http://localhost:%d%s", courseApp.getMappedPort(port), confirmOperation);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);

        assertEquals((HttpStatus.OK), response.getStatusCode());
        String operationId = response.getBody();

        Assertions.assertNotNull(operationId);
        Assertions.assertNotEquals(operationId, "");

    }

}
