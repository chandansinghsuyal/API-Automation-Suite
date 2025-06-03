package com.api.tests;

import com.api.models.Product;
import com.api.utils.SchemaValidator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Product Management")
@Feature("Product API")
public class ProductApiTests extends BaseTest {

    @Test(description = "Get product by ID")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Product Retrieval")
    public void testGetProductById() {
        Response response = get("/products/1");
        
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("name"), notNullValue());
        assertThat(response.jsonPath().getDouble("price"), greaterThan(0.0));
        SchemaValidator.validateResponseAgainstSchema(response.asString(), "product-schema.json");
    }

    @Test(description = "Create new product")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Product Creation")
    public void testCreateProduct() {
        Product newProduct = new Product();
        newProduct.setName("Test Product");
        newProduct.setDescription("Test Description");
        newProduct.setPrice(99.99);
        newProduct.setStock(100);
        newProduct.setCategory("Electronics");

        Response response = post("/products", newProduct);
        
        assertThat(response.getStatusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("id"), notNullValue());
        assertThat(response.jsonPath().getString("name"), equalTo(newProduct.getName()));
        SchemaValidator.validateResponseAgainstSchema(response.asString(), "product-schema.json");
    }

    @Test(description = "Update product")
    @Severity(SeverityLevel.NORMAL)
    @Story("Product Update")
    public void testUpdateProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(149.99);
        updatedProduct.setStock(50);

        Response response = put("/products/1", updatedProduct);
        
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("name"), equalTo(updatedProduct.getName()));
        assertThat(response.jsonPath().getDouble("price"), equalTo(updatedProduct.getPrice()));
        SchemaValidator.validateResponseAgainstSchema(response.asString(), "product-schema.json");
    }

    @Test(description = "Delete product")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Product Deletion")
    public void testDeleteProduct() {
        Response response = delete("/products/1");
        
        assertThat(response.getStatusCode(), equalTo(204));
    }

    @Test(description = "Get all products")
    @Severity(SeverityLevel.NORMAL)
    @Story("Product Listing")
    public void testGetAllProducts() {
        Response response = get("/products");
        
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getList("$"), not(empty()));
    }

    @Test(description = "Get products by category")
    @Severity(SeverityLevel.NORMAL)
    @Story("Product Filtering")
    public void testGetProductsByCategory() {
        Response response = get("/products?category=Electronics");
        
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getList("$"), not(empty()));
        response.jsonPath().getList("$").forEach(product -> 
            assertThat(((String) ((java.util.Map) product).get("category")), equalTo("Electronics"))
        );
    }
} 