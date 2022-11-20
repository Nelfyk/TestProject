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

    private JsonSimpleParser() {
    }

    public static void parse(String operation, String path) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(path)) {
            JSONObject rootJsonObject = (JSONObject) parser.parse(reader); // get root JSON file

            switch (operation.toLowerCase()) {
                case "search": {
                    parseSearch(rootJsonObject);
                    ;
                    break;
                }
                case "stat": {
                    parseStat(rootJsonObject);
                    break;
                }
                default: {
                    JsonWriter.writeError("operation");
                    break;
                }
            }
        } catch (Exception e) {
            if (e.getClass().equals(java.io.FileNotFoundException.class)) {
                JsonWriter.writeError("FileNotFoundException");
            } else {
                JsonWriter.writeError("jsonInput");
            }
        }

    }

    private static void parseSearch(JSONObject rootJsonObject) {
        JSONArray criteriasArray = (JSONArray) rootJsonObject.get("criterias");
        JSONObject criteriasData;

        for (int i = 0; i < criteriasArray.size(); i++) {
            criteriasData = (JSONObject) criteriasArray.get(i);

            switch (criteriasData.keySet().toString()) {
                case "[lastName]": {
                    JsonReader.criteriaList.add(new RequestOne(criteriasData.get("lastName").toString()));
                    break;
                }
                case "[minTimes, productName]": {
                    int minTimes = Integer.parseInt(criteriasData.get("minTimes").toString());
                    JsonReader.criteriaList.add(new RequestTwo(criteriasData.get("productName").toString(), minTimes));
                    break;
                }
                case "[minExpenses, maxExpenses]": {
                    int minExpenses = Integer.parseInt(criteriasData.get("minExpenses").toString());
                    int maxExpenses = Integer.parseInt(criteriasData.get("maxExpenses").toString());
                    JsonReader.criteriaList.add(new RequestThree(minExpenses, maxExpenses));
                    break;
                }
                case "[badCustomers]": {
                    int badCustomers = Integer.parseInt(criteriasData.get("badCustomers").toString());
                    JsonReader.criteriaList.add(new RequestFour(badCustomers));
                    break;
                }
            }
        }
    }

    private static void parseStat(JSONObject rootJsonObject) {
        try {
            String startDate = rootJsonObject.get("startDate").toString();
            String endDate = rootJsonObject.get("endDate").toString();
            // date format checker
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(startDate);
            df.parse(endDate);

            JsonReader.criteriaList.add(new Stat(startDate, endDate));
        } catch (ParseException e) {
            JsonWriter.writeError("dateError");
        }

    }
}