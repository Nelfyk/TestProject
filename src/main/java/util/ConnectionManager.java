package util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionManager {
    private static Connection dbConnection;
    private static String DB_CONNECTION;
    private static String DB_USER;
    private static String DB_PASSWORD;

    private ConnectionManager() {
    }

    public static void openConnection() {
        getConfigIni();
        dbConnection = ConnectionManager.getDBConnection();
        RequestHandler.setDbConnection(dbConnection);
    }

    public static Connection getDBConnection() {
        try {
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            JsonWriter.writeError("PSQLException");
            return null;
        }
    }

    public static void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getConfigIni() {
        try (InputStream input = Files.newInputStream(Paths.get("classes/config.ini"))) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value
            String host = prop.getProperty("DB_HOST");
            String port = prop.getProperty("DB_PORT");
            String name = prop.getProperty("DB_NAME");

            DB_CONNECTION = host + ":" + port + "/" + name;
            DB_USER = prop.getProperty("DB_USER");
            DB_PASSWORD = prop.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            JsonWriter.writeError("config.ini file is missing");
        }
    }
}
