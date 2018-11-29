package com.kikermint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {

    @Mock
    private Console console;
    private StatementPrinter statementPrinter;

    @Before
    public void setUp() {
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    public void print_all_transactions() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(LocalDateTime.of(2014, 4, 2, 1, 1), 900, -100),
                new Transaction(LocalDateTime.of(2014, 4, 10, 1, 1), 1400, 500));

        statementPrinter.printBody(transactions);

        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine("2014-04-10 | 500 | 1400");
        verify(console).printLine("2014-04-02 | -100 | 900");
    }
}