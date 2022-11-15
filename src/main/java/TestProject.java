import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestProject {
    static String DB_CONNECTION = "jdbc:postgresql://hostname:port/dbname";
    static String DB_USER = "username";
    static String DB_PASSWORD = "password";

    public static void main(String[] args){

    }

    // Создание подключение к БД
    private static Connection getDBConnection(){
        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch(ClassNotFoundException e){
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
        try{
            dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
            return dbConnection;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dbConnection;
    }

}

//        JSONObject sampleObject = new JSONObject();
//        sampleObject.put("name", "Stackabuser");
//        sampleObject.put("age", 35);
//
//        JSONArray messages = new JSONArray();
//        messages.add("Hey!");
//        messages.add("What's up?!");
//
//        sampleObject.put("messages", messages);
//        Files.write(Paths.get(filename), sampleObject.toJSONString().getBytes());


// {"name":"Stackabuser","messages":["Hey!","What's up?!"],"age":35}


//    javac Main.java
//    java Main arg0 arg1 arg2