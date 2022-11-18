package util;

import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class JsonSimpleParser {

    private JsonSimpleParser(){
    }

    public static void parse(int operation){ // 1 - search, 2 - stat

        JSONParser parser = new JSONParser();

        try(FileReader reader = new FileReader("input.json")){

            JSONObject rootJsonObject = (JSONObject) parser.parse(reader); // get root JSON file

            // selecting an operation
            if (operation == 1){
                parseSearch(rootJsonObject);
            } else {
                parseStat(rootJsonObject);
            }

        } catch (Exception e){
            System.out.println("File error: " + e.toString());
        }

    }
    private static void parseSearch(JSONObject rootJsonObject){
        JSONArray criteriasArray = (JSONArray) rootJsonObject.get("criterias");
        JSONObject criteriasData;

        for(int i=0;i<criteriasArray.size();i++){
            criteriasData = (JSONObject) criteriasArray.get(i);
            System.out.println(criteriasData.keySet());

            switch(criteriasData.keySet().toString()){
                case "[lastName]": {
                    JsonReader.criteriasList.add(new CriterionOne(criteriasData.get("lastName").toString()));
                    break;
                }
                case "[minTimes, productName]": {
                    int minTimes = Integer.parseInt(criteriasData.get("minTimes").toString());
                    JsonReader.criteriasList.add(new CriterionTwo(criteriasData.get("productName").toString(),minTimes));
                    break;
                }
                case "[minExpenses, maxExpenses]": {
                    int minExpenses = Integer.parseInt(criteriasData.get("minExpenses").toString());
                    int maxExpenses = Integer.parseInt(criteriasData.get("maxExpenses").toString());
                    JsonReader.criteriasList.add(new CriterionThree(minExpenses,maxExpenses));
                    break;
                }
                case "[badCustomers]": {
                    int badCustomers = Integer.parseInt(criteriasData.get("badCustomers").toString());
                    JsonReader.criteriasList.add(new CriterionFour(badCustomers));
                    break;
                }
            }
        }
    }
    private static void parseStat(JSONObject rootJsonObject){
        try {
            String startDate = rootJsonObject.get("startDate").toString();
            String endDate = rootJsonObject.get("endDate").toString();
            // date format checker
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(startDate);
            df.parse(endDate);

            JsonReader.criteriasList.add(new Stat(startDate,endDate));
        } catch (ParseException e) {
            System.out.println("Date format error: " + e.toString());
        }

    }
}
