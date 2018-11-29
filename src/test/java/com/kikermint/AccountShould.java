package com.kikermint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {
    private static final LocalDateTime DATE = LocalDateTime.of(2018, 11, 29, 20, 31, 2);
    @Mock
    private Clock clock;
    @Mock
    private StatementPrinter statementPrinter;

    private Account account;

    @Before
    public void setUp() {
        given(clock.now()).willReturn(DATE);
        account = new Account(clock, new ArrayList<>(), 1000, statementPrinter);
    }

    @Test
    public void account_start_with_one_thousand_balance() {
        assertThat(account.getBalance()).isEqualTo(1000);
    }

    @Test
    public void increase_deposit_amount() {
        account.deposit(900);

        Transaction transaction = new Transaction(DATE, 1900, 900);
        assertThat(account.getTransactions())
                .hasSize(1)
                .contains(transaction);
    }

    @Test
    public void withdraw_account() {
        account.withdraw(100);

        Transaction transaction = new Transaction(DATE, 900, -100);
        assertThat(account.getTransactions())
                .hasSize(1)
                .contains(transaction);

    }

    @Test
    public void obtain_two_transactions() {
        account.deposit(100);
        account.withdraw(900);
        assertThat(account.getTransactions())
                .hasSize(2)
                .contains(
                        new Transaction(DATE, 1100, 100),
                        new Transaction(DATE, 200, -900));
    }

    @Test
    public void print_all_transactions() {
        account.printStatement();

        verify(statementPrinter).printBody(new ArrayList<>());
    }
}