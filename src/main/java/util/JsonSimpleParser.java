package util;

import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonSimpleParser {

    private JsonSimpleParser(){
    }

    public static void parse(){

        JSONParser parser = new JSONParser();

        try(FileReader reader = new FileReader("input.json")){

            JSONObject rootJsonObject = (JSONObject) parser.parse(reader); // get root JSON file

            JSONArray criteriasArray = (JSONArray) rootJsonObject.get("criterias");
            JSONObject criteriasData;


//            System.out.println(criteriasArray.toJSONString());
            for(int i=0;i<criteriasArray.size();i++){
                criteriasData = (JSONObject) criteriasArray.get(i);
                System.out.println(criteriasData.keySet().toString());


//                if (criteriasData.keySet().toString().equals("[lastName]")) {
//                    Search.criteriasList.add(new CriterionOne(criteriasData.values().toString().replace("[", "")
//                            .replace("]", "")));
//                } else if(criteriasData.keySet().toString().equals("[minTimes]")) {
//                    System.out.println("shit");
//                }


                switch(criteriasData.keySet().toString()){
                    case "[lastName]": {
                        Search.criteriasList.add(new CriterionOne(criteriasData.get("lastName").toString()));
                        break;
                    }
                    case "[minTimes, productName]": {
                        int minTimes = Integer.parseInt(criteriasData.get("minTimes").toString());
                        Search.criteriasList.add(new CriterionTwo(criteriasData.get("productName").toString(),minTimes));
                        break;
                    }
                    case "[minExpenses, maxExpenses]": {
                        int minExpenses = Integer.parseInt(criteriasData.get("minExpenses").toString());
                        int maxExpenses = Integer.parseInt(criteriasData.get("maxExpenses").toString());
                        Search.criteriasList.add(new CriterionThree(minExpenses,maxExpenses));
                        break;
                    }
                    case "[badCustomers]": {
                        int badCustomers = Integer.parseInt(criteriasData.get("badCustomers").toString());
                        Search.criteriasList.add(new CriterionFour(badCustomers));
                        break;
                    }
                }

            }

        } catch (Exception e){
            System.out.println("Parsing error " + e.toString());
        }

    }
}
