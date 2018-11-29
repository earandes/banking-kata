package com.kikermint.acceptance;

import com.kikermint.Account;
import com.kikermint.Clock;
import com.kikermint.Console;
import com.kikermint.StatementPrinter;
import com.kikermint.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeatureShould {

    private static final ArrayList<Transaction> TRANSACTIONS = new ArrayList<>();
    @Mock
    Console console;
    @Mock
    Clock clock;

    private Account account;

    @Before
    public void setUp() {
        StatementPrinter statementPrinter = new StatementPrinter(console);
        account = new Account(clock, TRANSACTIONS, 0, statementPrinter);
    }

    @Test
    public void should_print_statement_containing_all_transactions() {
        given(clock.now())
                .willReturn(
                        LocalDateTime.of(2014, 4, 1, 1, 1),
                        LocalDateTime.of(2014, 4, 2, 1, 1),
                        LocalDateTime.of(2014, 4, 10, 1, 1));
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);

        account.printStatement();

        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine("2014-04-10 | 500 | 1400");
        verify(console).printLine("2014-04-02 | -100 | 900");
        verify(console).printLine("2014-04-01 | 1000 | 1000");
    }
}
