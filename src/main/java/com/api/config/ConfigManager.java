package com.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);
    private static ConfigManager instance;
    private EnvironmentConfig config;

    private ConfigManager() {
        loadConfig();
    }

    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadConfig() {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            String env = System.getProperty("env", "qa");
            File configFile = new File("src/main/resources/config/" + env + ".yaml");
            config = mapper.readValue(configFile, EnvironmentConfig.class);
            logger.info("Loaded configuration for environment: {}", env);
        } catch (IOException e) {
            logger.error("Failed to load configuration", e);
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public EnvironmentConfig getConfig() {
        return config;
    }

    public String getBaseUrl() {
        return config.getBaseUrl();
    }

    public String getApiKey() {
        return config.getApiKey();
    }
} 