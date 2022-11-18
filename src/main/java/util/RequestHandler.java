package util;

import model.RequestOne;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RequestHandler {
    public static Connection dbConnection;
    private RequestHandler(){
    }

    public static void start(){
        try {
            dbConnection = ConnectionManager.getDBConnection();
            System.out.println(dbConnection.getTransactionIsolation());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static List<RequestOne> requestOne(String requestText){
        String selectTableSQL = "SELECT * FROM customer where lastname like '"+requestText+"'";
        List<RequestOne> requestList = new ArrayList<>();
        try {
            Statement statement = dbConnection.createStatement();
            // выполнить SQL запрос
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                RequestOne tempRequestOne = new RequestOne(lastname);
                tempRequestOne.setFirstName(firstname);
                requestList.add(tempRequestOne);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return requestList;
    }
}
