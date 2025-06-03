package com.api.tests;

import com.api.models.Order;
import com.api.utils.SchemaValidator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Order Management")
@Feature("Order API")
public class OrderApiTests extends BaseTest {

    @Test(description = "Get order by ID")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Order Retrieval")
    public void testGetOrderById() {
        Response response = get("/orders/1");
        
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("id"), notNullValue());
        assertThat(response.jsonPath().getDouble("totalAmount"), greaterThan(0.0));
        SchemaValidator.validateResponseAgainstSchema(response.asString(), "order-schema.json");
    }

    @Test(description = "Create new order")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Order Creation")
    public void testCreateOrder() {
        Order newOrder = new Order();
        newOrder.setUserId("user123");
        
        List<Order.OrderItem> items = new ArrayList<>();
        Order.OrderItem item = new Order.OrderItem();
        item.setProductId("product123");
        item.setQuantity(2);
        item.setPrice(99.99);
        items.add(item);
        
        newOrder.setItems(items);
        newOrder.setTotalAmount(199.98);
        newOrder.setStatus("pending");

        Response response = post("/orders", newOrder);
        
        assertThat(response.getStatusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("id"), notNullValue());
        assertThat(response.jsonPath().getString("status"), equalTo("pending"));
        SchemaValidator.validateResponseAgainstSchema(response.asString(), "order-schema.json");
    }

    @Test(description = "Update order status")
    @Severity(SeverityLevel.NORMAL)
    @Story("Order Update")
    public void testUpdateOrderStatus() {
        Order updatedOrder = new Order();
        updatedOrder.setStatus("shipped");

        Response response = put("/orders/1/status", updatedOrder);
        
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("status"), equalTo("shipped"));
        SchemaValidator.validateResponseAgainstSchema(response.asString(), "order-schema.json");
    }

    @Test(description = "Cancel order")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Order Cancellation")
    public void testCancelOrder() {
        Response response = delete("/orders/1");
        
        assertThat(response.getStatusCode(), equalTo(204));
    }

    @Test(description = "Get all orders")
    @Severity(SeverityLevel.NORMAL)
    @Story("Order Listing")
    public void testGetAllOrders() {
        Response response = get("/orders");
        
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getList("$"), not(empty()));
    }

    @Test(description = "Get orders by user ID")
    @Severity(SeverityLevel.NORMAL)
    @Story("Order Filtering")
    public void testGetOrdersByUserId() {
        Response response = get("/orders?userId=user123");
        
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getList("$"), not(empty()));
        response.jsonPath().getList("$").forEach(order -> 
            assertThat(((String) ((java.util.Map) order).get("userId")), equalTo("user123"))
        );
    }
} 