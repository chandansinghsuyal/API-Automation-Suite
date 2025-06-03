package com.api.tests;

import com.api.models.User;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("User Management")
@Feature("User API")
public class UserApiTests extends BaseTest {

    @Test(description = "Get user by ID")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Retrieval")
    public void testGetUserById() {
        Response response = get("/users/1");
        
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("name"), notNullValue());
        assertThat(response.jsonPath().getString("email"), notNullValue());
    }

    @Test(description = "Create new user")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Creation")
    public void testCreateUser() {
        User newUser = new User();
        newUser.setName("John Doe");
        newUser.setEmail("john.doe@example.com");
        newUser.setRole("user");

        Response response = post("/users", newUser);
        
        assertThat(response.getStatusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("id"), notNullValue());
        assertThat(response.jsonPath().getString("name"), equalTo(newUser.getName()));
    }

    @Test(description = "Update user")
    @Severity(SeverityLevel.NORMAL)
    @Story("User Update")
    public void testUpdateUser() {
        User updatedUser = new User();
        updatedUser.setName("John Updated");
        updatedUser.setEmail("john.updated@example.com");

        Response response = put("/users/1", updatedUser);
        
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("name"), equalTo(updatedUser.getName()));
    }

    @Test(description = "Delete user")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Deletion")
    public void testDeleteUser() {
        Response response = delete("/users/1");
        
        assertThat(response.getStatusCode(), equalTo(204));
    }

    @Test(description = "Get all users")
    @Severity(SeverityLevel.NORMAL)
    @Story("User Listing")
    public void testGetAllUsers() {
        Response response = get("/users");
        
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getList("$"), not(empty()));
    }
} 