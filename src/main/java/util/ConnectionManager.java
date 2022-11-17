package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    final static String DB_CONNECTION = "jdbc:postgresql://localhost:5432/shop";
    final static String DB_USER = "postgres";
    final static String DB_PASSWORD = "psql";

    private ConnectionManager(){
    }

    public static Connection getDBConnection(){
        try{
            return DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
