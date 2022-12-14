package model;

// 2.	Название товара и число раз — поиск покупателей, купивших этот товар не менее, чем указанное число раз

public class RequestTwo {
    private String productName;
    private int minTimes;

    public RequestTwo(String productName, int minTimes) {
        this.productName = productName;
        this.minTimes = minTimes;
    }

    public String getProductName() {
        return productName;
    }

    public int getMinTimes() {
        return minTimes;
    }

    @Override
    public String toString() {
        return "CriterionTwo{" +
                "productName='" + productName + '\'' +
                ", minTimes=" + minTimes +
                '}';
    }
}
