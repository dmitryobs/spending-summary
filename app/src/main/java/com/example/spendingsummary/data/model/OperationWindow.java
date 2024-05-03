package com.example.spendingsummary.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationWindow implements Serializable {
    private String windowName;
    private List<Operation> operations;
    private Date creationTime;



    public OperationWindow(String windowName, Date creationTime) {
        this.windowName = windowName;
        this.operations = new ArrayList<>();
        this.creationTime = creationTime;
    }

    public void addExpense(int id, String name, double amount, Date date) {
        Expense expense = new Expense(this, id, name, amount, date);
        operations.add(expense);
    }
    public void addIncome(int id, String name, double amount, Date date) {
        Income income = new Income(this, id, name, amount, date);
        operations.add(income);
    }
    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

}
