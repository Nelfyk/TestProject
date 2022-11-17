import util.ConnectionManager;
import util.Search;

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
        Search.search();


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