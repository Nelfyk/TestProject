package util;

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

        JSONArray results = new JSONArray(); // Списки покупателей (Массив первого запроса)
        JSONObject criterion = new JSONObject(); // Критерий из запроса
        JSONObject criteriaArr = new JSONObject();  // {"lastName": "Иванов"}

        criteriaArr.put("lastName","Иванов");
        criterion.put("criteria",criteriaArr); // "criteria": {"lastName": "Иванов"}
        results.add(criterion); // далее будет
        root.put("results",results); // добавили массив первого запроса в root

        try {
            Files.write(Paths.get("output.json"), root.toJSONString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
