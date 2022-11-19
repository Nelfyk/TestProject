package util;

import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public static List<Object> criteriaList;
    private JsonReader(){
    }
    public static void read(){
        criteriaList = new ArrayList<>();
        JsonSimpleParser.parse(2); // 1 - search, 2 - stat
        if(true){
            StatHandler.getStatData();
        }
        System.out.println(criteriaList);

        /*
        приведение из object обратно в CriterionOne
        System.out.println(((CriterionOne)criteriaList.get(0)).getLastName());
         */
    }

}
