package com.rank.startup.utils;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

class CalculationsTest {

    @Test
    void givenBalanceAndAmount_WhenAddToBalance_ReturnResult() {
        BigDecimal balance  = Calculations.addToBalance(BigDecimal.valueOf(10), BigDecimal.valueOf(5));
        assertThat(balance).isEqualTo(BigDecimal.valueOf(15));
    }

    @Test
    void givenBalanceSubtractBalance_WhenAddToBalance_ReturnResult() {
        BigDecimal balance  = Calculations.subtractBalance(BigDecimal.valueOf(10), BigDecimal.valueOf(5));
        assertThat(balance).isEqualTo(BigDecimal.valueOf(5));
    }
}