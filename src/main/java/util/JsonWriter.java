package util;

import model.CriterionOne;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonWriter {

    private JsonWriter(){
    }
    public static void write(){

        JSONObject root = new JSONObject(); // main obj
        root.put("type", "search"); // Тип результата

        JSONArray customersList = new JSONArray(); // Списки покупателей (Массив первого запроса)
        JSONObject criterion = new JSONObject(); // Критерий из запроса
        JSONObject criteriaArr = new JSONObject();  // {"lastName": "Иванов"}
        JSONArray results = new JSONArray();
        JSONObject data = new JSONObject();
        for(int i=0;i<JsonReader.criteriasList.size();i++){
            if (JsonReader.criteriasList.get(i).getClass().equals(CriterionOne.class)){
                // from json input
                criteriaArr.put("lastName",((CriterionOne)JsonReader.criteriasList.get(i)).getLastName());
                criterion.put("criteria",criteriaArr); // "criteria": {"lastName": "Иванов"}

                // from dbShop
                data.put("lastName","Иванов");
                data.put("firstName","Антон");
                results.add(data);

                criterion.put("results",results);
                customersList.add(criterion); // add full obj criterion
            }
        }
        root.put("results",customersList); // добавили массив первого запроса в root

        try {
            Files.write(Paths.get("output.json"), root.toJSONString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
