package com.kikermint;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionShould {

    @Test
    public void obtain_date() {
        LocalDateTime dateTime = LocalDateTime.of(2014, 4, 1, 1, 1);
        Transaction transaction = new Transaction(dateTime, 1000, 0);

        String date = transaction.getDate();

        assertThat(date).isEqualTo("2014-04-01");
    }
}