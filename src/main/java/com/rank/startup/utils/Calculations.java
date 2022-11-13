package com.rank.startup.utils;

import java.math.BigDecimal;

public class Calculations {
    public static BigDecimal addToBalance(BigDecimal balance, BigDecimal amount) {
        return balance.add(amount);
    }

    public static BigDecimal subtractBalance(BigDecimal balance, BigDecimal amount) {
        return balance.subtract(amount);
    }
}
