package com.example.spendingsummary.data.model;

import java.util.Date;

public class Income extends Operation {

    public Income(OperationWindow window,int id, String name, double amount, Date date) {
        super(id,name, amount, date);
    }
}