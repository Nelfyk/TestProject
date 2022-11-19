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
        dbConnection = ConnectionManager.getDBConnection();
    }
    // Collecting data from db to list
    public static List<RequestOne> requestListCollector(String selectTableSQL){
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
    public static List<RequestOne> requestOne(String lastname){
        String selectTableSQL = "SELECT * FROM customer WHERE lastname LIKE '"+lastname+"'";
        return requestListCollector(selectTableSQL);
    }
    public static List<RequestOne> requestTwo(String productName,int minTimes){
        String selectTableSQL = "SELECT c.firstname,c.lastname,p.productName,count(*) FROM purchases pur " +
                "LEFT JOIN customer c ON c.id=pur.customer_id LEFT JOIN product p ON p.id=pur.product_id " +
                "WHERE p.productName LIKE '"+productName+ "' GROUP BY c.firstname,c.lastname,p.productName " +
                "HAVING count(*)>=" +minTimes;
        return requestListCollector(selectTableSQL);
    }
    public static List<RequestOne> requestThree(int minExpenses, int maxExpenses){
        String selectTableSQL = "SELECT c.firstname,c.lastname, sum(price) FROM purchases pur " +
                "LEFT JOIN customer c ON c.id=pur.customer_id LEFT JOIN product p ON p.id=pur.product_id " +
                "GROUP BY c.firstname,c.lastname HAVING sum(price) BETWEEN "+minExpenses+" AND "+maxExpenses;
        return requestListCollector(selectTableSQL);
    }
    public static List<RequestOne> requestFour(int badCustomers){
        String selectTableSQL = "SELECT c.firstname,c.lastname, sum(price) FROM purchases pur " +
                "LEFT JOIN customer c ON c.id=pur.customer_id LEFT JOIN product p ON p.id=pur.product_id " +
                "GROUP BY c.firstname,c.lastname ORDER BY sum LIMIT "+badCustomers;
        return requestListCollector(selectTableSQL);
    }
}
