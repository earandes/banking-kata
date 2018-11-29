package com.kikermint;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private LocalDateTime date;
    private int balance;
    private int amount;

    public Transaction(LocalDateTime date, int balance, int amount) {
        this.date = date;
        this.balance = balance;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return balance == that.balance &&
                amount == that.amount &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, balance, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", balance=" + balance +
                ", amount=" + amount +
                '}';
    }

    public String getDate() {
        return this.date.toLocalDate().toString();
    }

    public int getBalance() {
        return balance;
    }

    public int getAmount() {
        return amount;
    }
}
