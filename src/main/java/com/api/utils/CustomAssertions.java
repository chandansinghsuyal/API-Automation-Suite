package com.api.utils;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CustomAssertions {
    private static final Logger logger = LogManager.getLogger(CustomAssertions.class);

    public static void assertResponseTime(Response response, long maxTimeInMillis) {
        long responseTime = response.getTime();
        logger.info("Response time: {} ms", responseTime);
        assertThat("Response time should be less than " + maxTimeInMillis + " ms",
                responseTime, lessThan(maxTimeInMillis));
    }

    public static void assertResponseHeaders(Response response) {
        assertThat("Content-Type header should be present",
                response.getHeader("Content-Type"), notNullValue());
        assertThat("Content-Type should be application/json",
                response.getHeader("Content-Type"), containsString("application/json"));
    }

    public static void assertResponseStatus(Response response, int expectedStatusCode) {
        assertThat("Response status code should be " + expectedStatusCode,
                response.getStatusCode(), equalTo(expectedStatusCode));
    }

    public static void assertResponseBodyNotEmpty(Response response) {
        assertThat("Response body should not be empty",
                response.getBody().asString(), not(emptyString()));
    }

    public static void assertResponseContainsField(Response response, String fieldName) {
        assertThat("Response should contain field: " + fieldName,
                response.jsonPath().get(fieldName), notNullValue());
    }

    public static void assertResponseFieldEquals(Response response, String fieldName, Object expectedValue) {
        assertThat("Field " + fieldName + " should equal " + expectedValue,
                response.jsonPath().get(fieldName), equalTo(expectedValue));
    }

    public static void assertResponseArraySize(Response response, String arrayPath, int expectedSize) {
        assertThat("Array " + arrayPath + " should have size " + expectedSize,
                response.jsonPath().getList(arrayPath).size(), equalTo(expectedSize));
    }
} 