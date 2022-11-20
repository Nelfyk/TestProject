package util;

// Handling data before writing to json

import model.Customer;
import model.Stat;

import java.util.Collections;
import java.util.List;

public class StatHandler {

    private StatHandler(){
    }
    public static void getStatData(){
        String startDate = ((Stat)JsonReader.criteriaList.get(0)).getStartDate();
        String endDate = ((Stat)JsonReader.criteriaList.get(0)).getEndDate();
        List<Customer> customerList = RequestHandler.statRequest(startDate,endDate);

        for(Customer a: customerList){
            System.out.println(a.toString());
        }

        System.out.println("--------------------");
        Collections.sort(customerList,Customer.COMPARE_BY_TOTAL_EXPENSES);
        for(Customer a: customerList){
            System.out.println(a.toString());
        }
        System.out.println("totalDays : "+RequestHandler.getTotalDays(startDate,endDate));
    }
}
