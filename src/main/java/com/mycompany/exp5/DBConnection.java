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
    private static final String FALLBACK_URL = "jdbc:postgresql://db.nygekeajoxkpnjuzaiys.supabase.co:5432/postgres?sslmode=require";
    private static final String FALLBACK_USER = "postgres";
    private static final String FALLBACK_PASSWORD = "Bhavya@@1234";

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        String url = FALLBACK_URL;
        String user = FALLBACK_USER;
        String password = FALLBACK_PASSWORD;

        String catalinaBase = System.getProperty("catalina.base");
        if (catalinaBase != null && !catalinaBase.isBlank()) {
            Path configPath = Path.of(catalinaBase, "conf", CONFIG_FILE);
            if (Files.exists(configPath)) {
                Properties settings = new Properties();
                try (InputStream input = Files.newInputStream(configPath)) {
                    settings.load(input);
                } catch (IOException exception) {
                    throw new SQLException("Cannot read database configuration at " + configPath, exception);
                }

                String configUrl = settings.getProperty("url");
                String configUser = settings.getProperty("user");
                String configPassword = settings.getProperty("password");
                if (configUrl != null && !configUrl.isBlank()) {
                    url = configUrl;
                }
                if (configUser != null && !configUser.isBlank()) {
                    user = configUser;
                }
                if (configPassword != null && !configPassword.isBlank()) {
                    password = configPassword;
                }
            }
        }

        if (!url.trim().startsWith("jdbc:postgresql://")) {
            throw new SQLException("Use a JDBC URL beginning with jdbc:postgresql://");
        }
        if (password.contains("YOUR-PASSWORD")
                || password.contains("PASTE_YOUR_SUPABASE_DATABASE_PASSWORD_HERE")) {
            throw new SQLException("Replace the password placeholder in the database configuration.");
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException exception) {
            throw new SQLException("PostgreSQL JDBC driver is missing from the application.", exception);
        }
        return DriverManager.getConnection(url.trim(), user.trim(), password);
    }
}