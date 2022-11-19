package model;

// 3.	Минимальная и максимальная стоимость всех покупок — поиск покупателей,
//      у которых общая стоимость всех покупок за всё время попадает в интервал

public class RequestThree {
    private int minExpenses;
    private int maxExpenses;

    public RequestThree(int minExpenses, int maxExpenses) {
        this.minExpenses = minExpenses;
        this.maxExpenses = maxExpenses;
    }

    public int getMinExpenses() {
        return minExpenses;
    }

    public void setMinExpenses(int minExpenses) {
        this.minExpenses = minExpenses;
    }

    public int getMaxExpenses() {
        return maxExpenses;
    }

    public void setMaxExpenses(int maxExpenses) {
        this.maxExpenses = maxExpenses;
    }

    @Override
    public String toString() {
        return "CriterionThree{" +
                "minExpenses=" + minExpenses +
                ", maxExpenses=" + maxExpenses +
                '}';
    }
}
//