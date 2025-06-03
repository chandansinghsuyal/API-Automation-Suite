package com.api.tests;

import com.api.config.ConfigManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BaseTest {
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;
    protected static ConfigManager configManager;

    @BeforeClass
    public void setup() {
        configManager = ConfigManager.getInstance();
        setupRequestSpecification();
        setupResponseSpecification();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    private void setupRequestSpecification() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(configManager.getBaseUrl())
                .addHeader("Content-Type", "application/json")
                .addHeader("X-API-Key", configManager.getApiKey())
                .build();
    }

    private void setupResponseSpecification() {
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(configManager.getConfig().getTimeout()))
                .build();
    }

    @Step("Send GET request to {endpoint}")
    protected io.restassured.response.Response get(String endpoint) {
        logger.info("Sending GET request to: {}", endpoint);
        return given()
                .spec(requestSpec)
                .when()
                .get(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    @Step("Send POST request to {endpoint}")
    protected io.restassured.response.Response post(String endpoint, Object body) {
        logger.info("Sending POST request to: {} with body: {}", endpoint, body);
        return given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    @Step("Send PUT request to {endpoint}")
    protected io.restassured.response.Response put(String endpoint, Object body) {
        logger.info("Sending PUT request to: {} with body: {}", endpoint, body);
        return given()
                .spec(requestSpec)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }

    @Step("Send DELETE request to {endpoint}")
    protected io.restassured.response.Response delete(String endpoint) {
        logger.info("Sending DELETE request to: {}", endpoint);
        return given()
                .spec(requestSpec)
                .when()
                .delete(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }
} 