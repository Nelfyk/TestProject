import util.ConnectionManager;
import util.JsonReader;
import util.JsonWriter;

import java.sql.Connection;
import java.sql.SQLException;

public class TestProject {
    public static void main(String[] args){
        try {
            Connection dbConnection = ConnectionManager.getDBConnection();
            System.out.println(dbConnection.getTransactionIsolation());
            dbConnection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("------------------");

        JsonReader.search();
        JsonWriter.write();


    }
}

//    javac Main.java
//    java Main arg0 arg1 arg2