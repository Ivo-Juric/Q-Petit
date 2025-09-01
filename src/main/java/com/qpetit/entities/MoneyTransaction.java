package com.qpetit.entities;

import java.time.LocalDateTime;

public class MoneyTransaction {
    private int idTransaction;
    private LocalDateTime dateTransaction;
    private boolean isMoneyIncome;
    private double amount;
    private String description;
    private Event event;

    public MoneyTransaction(int idTransaction, LocalDateTime dateTransaction, boolean isMoneyIncome, double amount, String description, Event event) {
        this.idTransaction = idTransaction;
        this.dateTransaction = dateTransaction;
        this.isMoneyIncome = isMoneyIncome;
        this.amount = amount;
        this.description = description;
        this.event = event;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public boolean isMoneyIncome() {
        return isMoneyIncome;
    }

    public void setMoneyIncome(boolean isMoneyIncome) {
        this.isMoneyIncome = isMoneyIncome;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    
}
