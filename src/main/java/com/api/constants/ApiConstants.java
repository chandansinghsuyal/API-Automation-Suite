package com.api.constants;

public class ApiConstants {
    // HTTP Methods
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";
    public static final String PATCH = "PATCH";

    // HTTP Headers
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String AUTHORIZATION = "Authorization";
    public static final String API_KEY = "X-API-Key";
    public static final String ACCEPT = "Accept";
    public static final String USER_AGENT = "User-Agent";
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String NO_CACHE = "no-cache";

    // HTTP Status Codes
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int NO_CONTENT = 204;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int CONFLICT = 409;
    public static final int UNPROCESSABLE_ENTITY = 422;
    public static final int TOO_MANY_REQUESTS = 429;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int SERVICE_UNAVAILABLE = 503;

    // API Endpoints
    public static final String USERS_ENDPOINT = "/users";
    public static final String PRODUCTS_ENDPOINT = "/products";
    public static final String ORDERS_ENDPOINT = "/orders";
    public static final String AUTH_ENDPOINT = "/auth";
    public static final String HEALTH_ENDPOINT = "/health";

    // Query Parameters
    public static final String USER_ID = "userId";
    public static final String CATEGORY = "category";
    public static final String STATUS = "status";
    public static final String PAGE = "page";
    public static final String LIMIT = "limit";
    public static final String SORT = "sort";
    public static final String SEARCH = "search";

    // Order Status
    public static final String PENDING = "pending";
    public static final String PROCESSING = "processing";
    public static final String SHIPPED = "shipped";
    public static final String DELIVERED = "delivered";
    public static final String CANCELLED = "cancelled";
    public static final String REFUNDED = "refunded";

    // User Roles
    public static final String ADMIN = "admin";
    public static final String USER = "user";
    public static final String MANAGER = "manager";
    public static final String GUEST = "guest";

    // Response Fields
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";
    public static final String PRICE = "price";
    public static final String STOCK = "stock";
    public static final String TOTAL_AMOUNT = "totalAmount";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";
    public static final String MESSAGE = "message";
    public static final String ERROR = "error";
    public static final String STATUS_CODE = "statusCode";
    public static final String DATA = "data";
    public static final String META = "meta";
    public static final String PAGINATION = "pagination";

    // Time Constants
    public static final long DEFAULT_TIMEOUT = 30000; // 30 seconds
    public static final long SHORT_TIMEOUT = 5000;    // 5 seconds
    public static final long LONG_TIMEOUT = 60000;    // 1 minute

    // Validation Messages
    public static final String REQUIRED_FIELD = "This field is required";
    public static final String INVALID_EMAIL = "Invalid email format";
    public static final String INVALID_PASSWORD = "Password must be at least 8 characters";
    public static final String INVALID_STATUS = "Invalid status value";

    private ApiConstants() {
        // Private constructor to prevent instantiation
    }
} 