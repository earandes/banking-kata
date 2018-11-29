package com.kikermint;

import java.util.List;

import static java.util.Collections.reverse;

public class StatementPrinter {

    private Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void printBody(List<Transaction> transactions) {

        printHeader();

        reverse(transactions);
        transactions
                .forEach(transaction ->
                        console.printLine(printBody(transaction)));
    }



    private void printHeader() {
        console.printLine("DATE | AMOUNT | BALANCE");
    }

    private String printBody(Transaction transaction) {
        return transaction.getDate() + " | " + transaction.getAmount() + " | " + transaction.getBalance();
    }
}
