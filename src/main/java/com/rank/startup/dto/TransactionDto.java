package com.rank.startup.dto;

import com.rank.startup.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransactionDto {
    private Integer transactionId;

    @NotNull(message = "transactionType is required")
    private TransactionType transactionType;

    @NotNull(message = "amount is required")
    @Min(value = 0, message = "amount must be a positive number")
    private BigDecimal amount;
}
