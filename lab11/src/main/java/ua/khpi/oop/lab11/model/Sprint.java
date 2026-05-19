package ua.khpi.oop.lab11.model;

public class Sprint {

    private String name;
    private String startDate;
    private String endDate;

    public Sprint(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return name + " (" + startDate + " - " + endDate + ")";
    }
}