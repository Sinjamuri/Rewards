package com.example.demo.model;

import org.springframework.util.StringUtils;

import java.util.Date;

public class Transaction {
    private String name;
    private String date;
    private int amount;
    private int reward;
    private String month;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getMonth() {
        //return month;
        return StringUtils.delimitedListToStringArray(this.date,"-")[1];
    }

    public void setMonth(String month) {
        this.month = StringUtils.delimitedListToStringArray(this.date,"-")[1];
    }
}
