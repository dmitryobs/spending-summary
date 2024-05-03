package com.example.spendingsummary.data.model;

import java.util.Date;

public abstract class Operation {
    private String name;
    private double amount;
    private Date date;
    private int id;

    public Operation(int id,String name, double amount, Date date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}