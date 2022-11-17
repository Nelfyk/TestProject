package model;

// 2.	Название товара и число раз — поиск покупателей, купивших этот товар не менее, чем указанное число раз

public class CriterionTwo {
    private String productName;
    private int minTimes;

    public CriterionTwo(String productName, int minTimes) {
        this.productName = productName;
        this.minTimes = minTimes;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getMinTimes() {
        return minTimes;
    }

    public void setMinTimes(int minTimes) {
        this.minTimes = minTimes;
    }

    @Override
    public String toString() {
        return "CriterionTwo{" +
                "productName='" + productName + '\'' +
                ", minTimes=" + minTimes +
                '}';
    }
}
