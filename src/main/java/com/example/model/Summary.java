package com.example.model;

public class Summary {

    private String name;
    private int month1Total;
    private int month2Total;
    private int month3Total;
    private int threeMonthsTotal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonth1Total() {
        return month1Total;
    }

    public void setMonth1Total(int month1Total) {
        this.month1Total = month1Total;
    }

    public int getMonth2Total() {
        return month2Total;
    }

    public void setMonth2Total(int month2Total) {
        this.month2Total = month2Total;
    }

    public int getMonth3Total() {
        return month3Total;
    }

    public void setMonth3Total(int month3Total) {
        this.month3Total = month3Total;
    }

    public int getThreeMonthsTotal() {
        return threeMonthsTotal;
    }

    public void setThreeMonthsTotal(int threeMonthsTotal) {
        this.threeMonthsTotal = threeMonthsTotal;
    }
}
