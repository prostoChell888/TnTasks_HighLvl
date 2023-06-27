package ru.bahmutov.configuration;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ConfigurationDB {
    private final String url;
    private final String username;
    private final String password;

    public ConfigurationDB(String configFile) {
        Properties properties = loadProperties(configFile);
        this.url = properties.getProperty("datasource.url");
        this.username = properties.getProperty("datasource.username");
        this.password = properties.getProperty("datasource.password");
    }

    public ConfigurationDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Properties loadProperties(String configFile) {
        Properties properties = new Properties();
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFile)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
