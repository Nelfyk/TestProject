package util;

import model.CriterionOne;

import java.util.ArrayList;
import java.util.List;

public class Search {
    public static List<Object> criteriasList;
    private Search(){
    }
    public static void search(){
        criteriasList = new ArrayList<>();
        JsonSimpleParser.parse();

        System.out.println("\n");
        System.out.println(criteriasList);

        /*
        приведение из object обратно в CriterionOne
        System.out.println(((CriterionOne)criteriasList.get(0)).getLastName());
         */
    }

}