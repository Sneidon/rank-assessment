package com.rank.startup.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
public class PlayerDto {
    private Integer playerId;
    private BigDecimal balance;
    private List<TransactionDto> transactions;
}
