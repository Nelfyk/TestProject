package model;

// 4.	Число пассивных покупателей — поиск покупателей, купивших меньше всего товаров.
//      Возвращается не более, чем указанное число покупателей.

public class RequestFour {
    private int badCustomers;

    public RequestFour(int badCustomers) {
        this.badCustomers = badCustomers;
    }

    public int getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(int badCustomers) {
        this.badCustomers = badCustomers;
    }

    @Override
    public String toString() {
        return "CriterionFour{" +
                "badCustomers=" + badCustomers +
                '}';
    }

}
