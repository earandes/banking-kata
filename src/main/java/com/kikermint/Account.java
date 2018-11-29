package com.kikermint;

import java.util.List;

public class Account {

    private Clock clock;
    private List<Transaction> transactions;
    private Integer balance;
    private StatementPrinter statementPrinter;

    public Account(Clock clock, List<Transaction> transactions, Integer balance, StatementPrinter statementPrinter) {
        this.clock = clock;
        this.transactions = transactions;
        this.balance = balance;
        this.statementPrinter = statementPrinter;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Integer getBalance() {
        return balance;
    }

    private void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void deposit(int deposit) {
        int depositBalance = this.getBalance() + deposit;
        this.setBalance(depositBalance);
        this.transactions.add(new Transaction(clock.now(), depositBalance, deposit));
    }

    public void withdraw(int withdraw) {
        int withdrawBalance = this.getBalance() - withdraw;
        this.setBalance(withdrawBalance);
        this.transactions.add(new Transaction(clock.now(), withdrawBalance, -withdraw));
    }

    public void printStatement() {
        statementPrinter.printBody(transactions);
    }

}
