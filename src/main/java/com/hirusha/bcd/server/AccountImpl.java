package com.hirusha.bcd.server;

import BankingApp.AccountPOA;
import BankingApp.InsufficientBalance;

import java.util.HashMap;

public class AccountImpl extends AccountPOA {

    private HashMap<String, Double> db = new HashMap<>();

    public AccountImpl() {
        db.put("ACC001", 10000.0);
        db.put("ACC002", 20000.0);
        db.put("ACC003", 30000.0);
    }

    @Override
    public double getBalance(String accNo) {
        return db.getOrDefault(accNo, 0.0);
    }

    @Override
    public void deposit(String accNo, double amount) {
        double currentBalance = db.getOrDefault(accNo, 0.0);
        db.put(accNo, currentBalance + amount);
        System.out.println("Server Log: Deposited " + amount + " to account " + accNo + ". New balance: " + db.get(accNo));
    }

    @Override
    public void withdraw(String accNo, double amount) throws InsufficientBalance {
        double currentBalance = db.getOrDefault(accNo, 0.0);
        if (currentBalance < amount) {
            throw new InsufficientBalance("Insufficient balance in account " + accNo);
        }
        db.put(accNo, currentBalance - amount);
        System.out.println("Server Log: Withdrew " + amount + " from account " + accNo + ". New balance: " + db.get(accNo));

    }
}
