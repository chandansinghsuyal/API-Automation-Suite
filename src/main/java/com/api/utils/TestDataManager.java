package com.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TestDataManager {
    private static final Logger logger = LogManager.getLogger(TestDataManager.class);
    private static final String TEST_DATA_DIR = "src/test/resources/testdata/";
    private static TestDataManager instance;
    private Map<String, Object> testData;

    private TestDataManager() {
        loadTestData();
    }

    public static synchronized TestDataManager getInstance() {
        if (instance == null) {
            instance = new TestDataManager();
        }
        return instance;
    }

    private void loadTestData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String env = System.getProperty("env", "qa");
            File testDataFile = new File(TEST_DATA_DIR + env + "-test-data.json");
            testData = mapper.readValue(testDataFile, Map.class);
            logger.info("Loaded test data for environment: {}", env);
        } catch (IOException e) {
            logger.error("Failed to load test data", e);
            throw new RuntimeException("Failed to load test data", e);
        }
    }

    public Object getTestData(String key) {
        return testData.get(key);
    }

    public String getString(String key) {
        return (String) getTestData(key);
    }

    public Integer getInteger(String key) {
        return (Integer) getTestData(key);
    }

    public Double getDouble(String key) {
        return (Double) getTestData(key);
    }

    public Boolean getBoolean(String key) {
        return (Boolean) getTestData(key);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getMap(String key) {
        return (Map<String, Object>) getTestData(key);
    }
} 