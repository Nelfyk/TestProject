package util;

import model.RequestOne;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
        JSONObject data;

        for(int i=0;i<JsonReader.criteriasList.size();i++){
            if (JsonReader.criteriasList.get(i).getClass().equals(RequestOne.class)){
                // from json input
                String lastName = ((RequestOne)JsonReader.criteriasList.get(i)).getLastName();
                criteriaArr.put("lastName",lastName);
                criterion.put("criteria",criteriaArr); // "criteria": {"lastName": "Иванов"}
                List<RequestOne> requestList = RequestHandler.requestOne(lastName);
                System.out.println(requestList);
                // from dbShop
                for(int j=0;j<requestList.size();j++) {
                    data = new JSONObject();
                    data.put("lastName", requestList.get(j).getLastName());
                    data.put("firstName", requestList.get(j).getFirstName());
                    System.out.println(requestList.get(j).getLastName() + " " + requestList.get(j).getFirstName());
                    results.add(data);
                }
                
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
