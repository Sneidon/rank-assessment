package com.rank.startup.enums;

public enum TransactionType {
    WAGER("WAGER"),
    WIN("WIN");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String toString() {
        return this.description;
    }
}
