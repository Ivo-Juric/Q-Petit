package controllers;

import entitys.Employee;

public interface MoneyAdministration {
    void newTransaction();
    void getTransactions();
    void getBalance();

}
