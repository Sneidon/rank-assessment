package com.rank.startup.entities;

import com.rank.startup.enums.TransactionType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "transaction")
public class Transaction extends Abstract {
    @NotNull(message = "transactionType is required field")
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @NotNull(message = "amount is required field")
    @Positive(message = "amount must be a positive number")
    @Column(name = "amount")
    private BigDecimal amount;

    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @ManyToOne
    private Player player;
}
