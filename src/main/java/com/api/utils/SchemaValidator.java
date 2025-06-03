package com.api.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SchemaValidator {
    private static final Logger logger = LogManager.getLogger(SchemaValidator.class);
    private static final String SCHEMA_DIR = "src/main/resources/schemas/";

    public static JsonSchemaValidator getSchemaValidator(String schemaFileName) {
        try {
            String schemaPath = SCHEMA_DIR + schemaFileName;
            File schemaFile = new File(schemaPath);
            
            if (!schemaFile.exists()) {
                throw new IOException("Schema file not found: " + schemaPath);
            }

            String schemaContent = new String(Files.readAllBytes(Paths.get(schemaPath)));
            logger.info("Loaded schema from file: {}", schemaFileName);
            
            return JsonSchemaValidator.matchesJsonSchema(schemaContent);
        } catch (IOException e) {
            logger.error("Failed to load schema file: {}", schemaFileName, e);
            throw new RuntimeException("Failed to load schema file: " + schemaFileName, e);
        }
    }

    public static void validateResponseAgainstSchema(String response, String schemaFileName) {
        try {
            JsonSchemaValidator validator = getSchemaValidator(schemaFileName);
            validator.validate(response);
            logger.info("Response validated successfully against schema: {}", schemaFileName);
        } catch (Exception e) {
            logger.error("Schema validation failed for schema: {}", schemaFileName, e);
            throw new RuntimeException("Schema validation failed", e);
        }
    }
} 