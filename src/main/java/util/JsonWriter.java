package util;

import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class JsonWriter {
    private static JSONObject root = new JSONObject(); // main obj
    private static JSONArray customersList = new JSONArray(); // Списки покупателей (Массив первого запроса)
    private static JSONObject criterion; // Критерий из запроса
    private static JSONObject criteriaArr;  // {"lastName": "Иванов"}
    private static JSONArray results;
    private static JSONObject data;

    private JsonWriter(){
    }

    public static void writeSearch(){
        root.put("type", "search"); // Тип результата
        for(int i = 0; i<JsonReader.criteriaList.size(); i++){
            // if Request #1
            if (JsonReader.criteriaList.get(i).getClass().equals(RequestOne.class)){
                newJsonObjects();
                // from json input
                String lastName = ((RequestOne)JsonReader.criteriaList.get(i)).getLastName();
                criteriaArr.put("lastName",lastName);
                criterion.put("criteria",criteriaArr); // "criteria": {"lastName": "Иванов"}
                // получаем лист данных из запроса #1
                List<RequestOne> requestList = RequestHandler.requestOne(lastName);
                // Извлечение данных DB из списка и добавление в json
                extractDataFromList(requestList);
                criterion.put("results",results);
                customersList.add(criterion); // add full obj criterion
            }
            // if Request #2
            if (JsonReader.criteriaList.get(i).getClass().equals(RequestTwo.class)){
                newJsonObjects();
                // from json input
                String productName = ((RequestTwo)JsonReader.criteriaList.get(i)).getProductName();
                int minTimes = ((RequestTwo)JsonReader.criteriaList.get(i)).getMinTimes();
                criteriaArr.put("productName",productName);
                criteriaArr.put("minTimes",minTimes);
                criterion.put("crtiteria",criteriaArr); // "criteria": {"productName": "Минеральная вода", "minTimes": 5}
                // получаем лист данных из запроса #2
                List<RequestOne> requestList = RequestHandler.requestTwo(productName,minTimes);
                // Извлечение данных DB из списка и добавление в json
                extractDataFromList(requestList);
                criterion.put("results",results);
                customersList.add(criterion); // add full obj criterion
            }
            // if Request #3
            if (JsonReader.criteriaList.get(i).getClass().equals(RequestThree.class)){
                newJsonObjects();
                int minExpenses = ((RequestThree)JsonReader.criteriaList.get(i)).getMinExpenses();
                int maxExpenses = ((RequestThree)JsonReader.criteriaList.get(i)).getMaxExpenses();
                criteriaArr.put("minExpenses",minExpenses);
                criteriaArr.put("maxExpenses",maxExpenses);
                criterion.put("crtiteria",criteriaArr); // "criteria": {"minExpenses": 112, "maxExpenses": 4000}
                // получаем лист данных из запроса #3
                List<RequestOne> requestList = RequestHandler.requestThree(minExpenses,maxExpenses);
                // Извлечение данных DB из списка и добавление в json
                extractDataFromList(requestList);
                criterion.put("results",results);
                customersList.add(criterion); // add full obj criterion
            }
            // if Request #4
            if (JsonReader.criteriaList.get(i).getClass().equals(RequestFour.class)){
                newJsonObjects();
                int badCustomers = ((RequestFour)JsonReader.criteriaList.get(i)).getBadCustomers();
                criteriaArr.put("badCustomers",badCustomers);
                criterion.put("crtiteria",criteriaArr); // "criteria": {"badCustomers": 3}
                // получаем лист данных из запроса #4
                List<RequestOne> requestList = RequestHandler.requestFour(badCustomers);
                // Извлечение данных DB из списка и добавление в json
                extractDataFromList(requestList);
                criterion.put("results",results);
                customersList.add(criterion); // add full obj criterion
            }
            root.put("results",customersList); // добавили массив первого запроса в root
        }

        try {
            Files.write(Paths.get("output.json"), root.toJSONString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void newJsonObjects(){
        criteriaArr = new JSONObject();
        criterion = new JSONObject();
        results = new JSONArray();
    }
    private static void extractDataFromList(List<RequestOne> requestList){
        for(int j=0;j<requestList.size();j++) {
            data = new JSONObject();
            data.put("lastName", requestList.get(j).getLastName());
            data.put("firstName", requestList.get(j).getFirstName());
//            System.out.println(requestList.get(j).getLastName() + " " + requestList.get(j).getFirstName());
            results.add(data);
        }
    }
    public static void writeStatJson(){
        JSONArray custromers = new JSONArray();
        JSONArray purchases = new JSONArray();
        JSONObject purchasesData = new JSONObject();

        int totalExpenses = 0;
        String startDate = ((Stat)JsonReader.criteriaList.get(0)).getStartDate();
        String endDate = ((Stat)JsonReader.criteriaList.get(0)).getEndDate();
        List<Customer> customerList = RequestHandler.statRequest(startDate,endDate);
        // Сортировка по убыванию totalExpenses
        Collections.sort(customerList,Customer.COMPARE_BY_TOTAL_EXPENSES);

        for(Customer a: customerList){
            System.out.println(a.toString());
        }
        // Запись в JSON
        root.put("type", "stat");
        root.put("totalDays", RequestHandler.getTotalDays(startDate,endDate));
        // beginning
        for(int i = 0; i<customerList.size();i++) {
            data = new JSONObject();
            purchases = new JSONArray();
            data.put("name",customerList.get(i).getName());
            for(int j = 0; j<customerList.get(i).getProductList().size();j++) {
                purchasesData = new JSONObject();
                // Поля продуктов
                String productName = customerList.get(i).getProductList().get(j).getProductName();
                int price = customerList.get(i).getProductList().get(j).getPrice();
                //
                purchasesData.put("name",productName);
                purchasesData.put("expenses",price);

                purchases.add(purchasesData);
            }
            data.put("purchases",purchases);
            data.put("totalExpenses",customerList.get(i).getTotalExpenses());
            totalExpenses+=customerList.get(i).getTotalExpenses();
            custromers.add(data);
        }
        root.put("customers",custromers);
        root.put("totalExpenses", totalExpenses);
        root.put("avgExpenses", (double)totalExpenses/customerList.size());

        try {
            Files.write(Paths.get("output.json"), root.toJSONString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
