package com.mycompany.exp5;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DBConnection {
    private static final String CONFIG_FILE = "student-db.properties";

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        String catalinaBase = System.getProperty("catalina.base");
        if (catalinaBase == null || catalinaBase.isBlank()) {
            throw new SQLException("Tomcat's catalina.base system property is not set.");
        }

        Path configPath = Path.of(catalinaBase, "conf", CONFIG_FILE);
        Properties settings = new Properties();
        try (InputStream input = Files.newInputStream(configPath)) {
            settings.load(input);
        } catch (IOException exception) {
            throw new SQLException("Cannot read database configuration at " + configPath, exception);
        }

        String url = settings.getProperty("url");
        String user = settings.getProperty("user");
        String password = settings.getProperty("password");
        if (url == null || user == null || password == null
                || url.isBlank() || user.isBlank()) {
            throw new SQLException("Set url, user, and password in " + configPath);
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException exception) {
            throw new SQLException("PostgreSQL JDBC driver is missing from the application.", exception);
        }
        return DriverManager.getConnection(url.trim(), user.trim(), password);
    }
}