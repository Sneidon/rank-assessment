package com.rank.startup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsRequestDto {
    @Size(min = 1, max = 50, message = "username must be between 1 and 50 characters")
    @NotNull(message = "username is required")
    private String username;
}
