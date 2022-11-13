package com.rank.startup.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "player")
public class Player extends Abstract {
    @Column(name = "username")
    private String username;

    @NotNull(message = "balance is required field")
    @Column(name = "balance")
    private BigDecimal balance;

    @OneToMany(mappedBy = "player", orphanRemoval = true, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Transaction> transactions;
}
