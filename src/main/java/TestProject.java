import util.JsonReader;
import util.JsonWriter;
import util.RequestHandler;

import java.sql.SQLException;

public class TestProject {
    public static void main(String[] args){
        RequestHandler.start();
        System.out.println("------------------");
        JsonReader.read();
        JsonWriter.write();

        try {
            RequestHandler.dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

//    javac Main.java
//    java Main arg0 arg1 arg2