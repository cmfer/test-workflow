package org.mps.bank;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private int balance = 0;
    private List<BankMovement> movements;
    public List<BankMovement> getMovements() {
        return movements;
    }
    public void setMovements(List<BankMovement> movements) {
        this.movements = movements;
    }
    public BankAccount(int startingBalance) {
        this.balance = startingBalance;
        this.movements = new ArrayList<>();
    }
    public boolean withdraw(int amount) {
        if(balance >= amount) {
            balance -= amount;
            this.movements.add(new BankMovement(BankMovement.MOVEMENT_TYPE.WITHDRAW, amount));
            return true;
        }
        return false;
    }
    public int deposit(int amount) {
        if (amount <0)
            throw new IllegalArgumentException("Amount cannot be negative");
        balance += amount;
        this.movements.add(new BankMovement(BankMovement.MOVEMENT_TYPE.DEPOSIT, amount));
        return balance;
    }
    public int getBalance() {
        return balance;
    }

    // Calculate the payment per month for a loan
    public double payment(double total_amount, double interest, int npayments){
        return total_amount*(interest*Math.pow((1+interest), npayments)/(Math.pow((1+interest), npayments)-1));
    }

    // Calculate the pending amount for a loan in a month
    public double pending (double amount, double inte, int npayments, int month){
        double res;
        if(month==0){
            res=amount;
        }else{
            double ant=pending(amount, inte, npayments, month-1);
            res = ant - (payment(amount,inte,npayments) - inte*ant);
        }
        return res;
    }
}
