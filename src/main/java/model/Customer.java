package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Customer {
    private int id;
    private String name; // firstname + lastname
    private List<Product> productList = new ArrayList<>();
    private int totalExpenses = 0;

    public Customer(int id, String name, String productName, int price) {
        this.id = id;
        this.name = name;
        productList.add(new Product(productName, price));
        totalExpenses += price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public void incrementTotalExpenses(int totalExpenses) {
        this.totalExpenses += totalExpenses;
    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productList=" + productList +
                ", totalExpenses=" + totalExpenses +
                '}';
    }

    // Сортировка листа по убыванаию totalExpenses
    public static final Comparator<Customer> COMPARE_BY_TOTAL_EXPENSES = new Comparator<Customer>() {
        @Override
        public int compare(Customer lhs, Customer rhs) {
            return rhs.getTotalExpenses() - lhs.getTotalExpenses();
        }
    };
}
