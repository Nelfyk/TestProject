package util;

import model.Customer;
import model.Product;
import model.RequestOne;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RequestHandler {
    private static Connection dbConnection;

    private RequestHandler() {
    }

    // Collecting data from db to list
    public static List<RequestOne> requestListCollector(String selectTableSQL) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }

    public static List<RequestOne> requestOne(String lastname) {
        String selectTableSQL = "SELECT * FROM customer WHERE lastname LIKE '" + lastname + "'";
        return requestListCollector(selectTableSQL);
    }

    public static List<RequestOne> requestTwo(String productName, int minTimes) {
        String selectTableSQL = "SELECT c.firstname,c.lastname,p.productName,count(*) FROM purchases pur " +
                "LEFT JOIN customer c ON c.id=pur.customer_id LEFT JOIN product p ON p.id=pur.product_id " +
                "WHERE p.productName LIKE '" + productName + "' GROUP BY c.firstname,c.lastname,p.productName " +
                "HAVING count(*)>=" + minTimes;
        return requestListCollector(selectTableSQL);
    }

    public static List<RequestOne> requestThree(int minExpenses, int maxExpenses) {
        String selectTableSQL = "SELECT c.firstname,c.lastname, sum(price) FROM purchases pur " +
                "LEFT JOIN customer c ON c.id=pur.customer_id LEFT JOIN product p ON p.id=pur.product_id " +
                "GROUP BY c.firstname,c.lastname HAVING sum(price) BETWEEN " + minExpenses + " AND " + maxExpenses;
        return requestListCollector(selectTableSQL);
    }

    public static List<RequestOne> requestFour(int badCustomers) {
        String selectTableSQL = "SELECT c.firstname,c.lastname, sum(price) FROM purchases pur " +
                "LEFT JOIN customer c ON c.id=pur.customer_id LEFT JOIN product p ON p.id=pur.product_id " +
                "GROUP BY c.firstname,c.lastname ORDER BY sum LIMIT " + badCustomers;
        return requestListCollector(selectTableSQL);
    }

    public static int getTotalDays(String startDate, String endDate) {
        String selectTableSQL = "SELECT DATE_PART('day','" + endDate + "'::timestamp - '" + startDate + "'::timestamp) as date";
        int totalDays = -1;
        try {
            Statement statement = dbConnection.createStatement();
            // выполнить SQL запрос
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                totalDays = rs.getInt("date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalDays;
    }

    public static List<Customer> statRequest(String startDate, String endDate) {
        String selectTableSQL = "SELECT pur.date,pur.customer_id,c.firstname,c.lastname,p.productname,p.price " +
                "FROM purchases pur LEFT JOIN customer c ON c.id=pur.customer_id " +
                "LEFT JOIN product p ON p.id=pur.product_id WHERE date BETWEEN '" + startDate + "' AND '" + endDate + "' " +
                "ORDER BY pur.customer_id";
        List<Customer> customerList = new ArrayList<>();
        try {
            Statement statement = dbConnection.createStatement();
            // выполнить SQL запрос
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                int id = rs.getInt("customer_id");
                String name = rs.getString("lastname") + " " + rs.getString("firstname");
                String productName = rs.getString("productName");
                int price = rs.getInt("price");
                boolean customerExist = false;
                boolean productExist = false;
                for (int i = 0; i < customerList.size(); i++) {
                    // здесь мы пробегаем по листу и проверяем, есть ли такой же id покупателя,
                    // если есть, то do same with products.
                    if (customerList.get(i).getId() == id) {
                        for (int j = 0; j < customerList.get(i).getProductList().size() && !customerExist; j++) {
                            if (customerList.get(i).getId() == id) {
                                customerExist = true;
                                for (int z = 0; z < customerList.get(i).getProductList().size() && !productExist; z++) {
                                    if (customerList.get(i).getProductList().get(z).getProductName().equals(productName)) {
                                        productExist = true;
                                        customerList.get(i).getProductList().get(z).incrementPrice(price);
                                        customerList.get(i).incrementTotalExpenses(price);
                                        //break; // заменил условием в for
                                    }
                                }
                                if (!productExist) {
                                    customerList.get(i).getProductList().add(new Product(productName, price));
                                    customerList.get(i).incrementTotalExpenses(price);
                                    // сделать функцию по удобному увеличению общей стоимости
                                }
                                //break; // заменил условием в for
                            }
                        }
                    }
                }
                if (!customerExist) {
                    customerList.add(new Customer(id, name, productName, price));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public static void setDbConnection(Connection dbConnection) {
        RequestHandler.dbConnection = dbConnection;
    }
}
