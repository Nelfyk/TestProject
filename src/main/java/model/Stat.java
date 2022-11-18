package model;


public class Stat {
    private String startDate;
    private String endDate;

    public Stat(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
