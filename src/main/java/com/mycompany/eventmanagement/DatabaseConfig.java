package com.mycompany.eventmanagement;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDatabaseUrl() {
        return properties.getProperty("DB_URL");
    }

    public static String getUsername() {
        return properties.getProperty("DB_USERNAME");
    }

    public static String getPassword() {
        return properties.getProperty("DB_PASSWORD");
    }
}

