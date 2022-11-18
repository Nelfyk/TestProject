package util;

import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public static List<Object> criteriasList;
    private JsonReader(){
    }
    public static void read(){
        criteriasList = new ArrayList<>();
        JsonSimpleParser.parse(1); // 1 - search, 2 - stat

        System.out.println(criteriasList);

        /*
        приведение из object обратно в CriterionOne
        System.out.println(((CriterionOne)criteriasList.get(0)).getLastName());
         */
    }

}
