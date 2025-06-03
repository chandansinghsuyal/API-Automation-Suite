# API Automation Suite

A comprehensive API automation testing framework built with REST Assured, TestNG, and other modern testing tools.

## Features

- REST API testing with REST Assured
- Data-driven testing support
- JSON Schema validation
- Comprehensive reporting with Allure
- Environment configuration management
- Logging with Log4j2
- CI/CD integration ready
- Parallel test execution
- Request/Response logging
- Custom assertions and validations

## Project Structure

```
api-automation-suite/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── api/
│   │   │           ├── config/
│   │   │           ├── models/
│   │   │           ├── utils/
│   │   │           └── constants/
│   │   └── resources/
│   │       ├── config/
│   │       ├── schemas/
│   │       └── testdata/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── api/
│       │           ├── tests/
│       │           └── steps/
│       └── resources/
│           └── testdata/
├── pom.xml
├── testng.xml
└── README.md
```

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Allure Command Line Tool (for report generation)

## Setup

1. Clone the repository
2. Install dependencies:
   ```bash
   mvn clean install
   ```

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific test suite
```bash
mvn clean test -Dsuite=regression
```

### Generate Allure Report
```bash
mvn allure:serve
```

## Test Data Management

Test data is managed through JSON files in `src/test/resources/testdata/` directory. Each test suite can have its own test data file.

## Configuration

Environment configurations are managed in `src/main/resources/config/` directory. Different environments (dev, qa, prod) can be configured separately.

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 