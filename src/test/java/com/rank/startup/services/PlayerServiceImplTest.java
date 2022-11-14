package com.rank.startup.services;

import com.rank.startup.dto.PlayerDto;
import com.rank.startup.dto.TransactionDto;
import com.rank.startup.dto.TransactionResponseDto;
import com.rank.startup.enums.TransactionType;
import com.rank.startup.exceptions.PlayerNotFoundException;
import com.rank.startup.exceptions.WagerGreaterThanBalanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {
    @Mock
    PlayerService playerService;

    @Test
    void givenExistingPlayerId_WhenFindPlayer_ReturnPlayer() {
        when(playerService.findPlayer(1)).thenReturn(new PlayerDto(1, BigDecimal.ONE));
        assertThat(playerService.findPlayer(1)).isNotNull();
    }

    @Test
    void givenNonExistingPlayerId_WhenFindPlayer_ShouldThrowPlayerNotFoundException() {
        when(playerService.findPlayer(1)).thenThrow(new PlayerNotFoundException(String.format("Player %o not found", 1)));
        PlayerNotFoundException thrown = Assertions.assertThrows(PlayerNotFoundException.class, () -> {
            playerService.findPlayer(1);
        });
        assertThat(String.format("Player %o not found", 1)).isEqualTo(thrown.getMessage());
    }

    @Test
    void givenTransactionDto_WhenUpdateBalance_ReturnTransactionResponseDto() {
        TransactionDto transactionDto = TransactionDto.builder().amount(BigDecimal.valueOf(10)).transactionType(TransactionType.WAGER).build();
        when(playerService.updateBalance(10, transactionDto)).thenReturn(new TransactionResponseDto(1, BigDecimal.TEN));
        TransactionResponseDto responseDto = playerService.updateBalance(10, transactionDto);
        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getTransactionId()).isEqualTo(1);
    }

    @Test
    void givenTransactionDtoWagerIsGreaterThanBalance_WhenUpdateBalance_ThrowWagerGreaterThanBalanceException() {
        TransactionDto transactionDto = TransactionDto.builder().amount(BigDecimal.valueOf(10)).transactionType(TransactionType.WAGER).build();
        when(playerService.updateBalance(1, transactionDto)).thenThrow(new WagerGreaterThanBalanceException(String.format("Wager %.2f is greater than balance. Player balance is %.2f", transactionDto.getAmount(), BigDecimal.ONE)));
        WagerGreaterThanBalanceException thrown = Assertions.assertThrows(WagerGreaterThanBalanceException.class, () -> {
            playerService.updateBalance(1, transactionDto);
        });
        assertThat(String.format("Wager %.2f is greater than balance. Player balance is %.2f", BigDecimal.TEN, BigDecimal.ONE)).isEqualTo(thrown.getMessage());
    }

    @Test
    void givenExistingUsername_WhenFindPlayerLatestTransactions_ReturnTransactions() {
        when(playerService.findPlayerLatestTransactions("john")).thenReturn(Arrays.asList(new TransactionDto(1, TransactionType.WAGER, BigDecimal.ONE)));
        List<TransactionDto> transactionDtos = playerService.findPlayerLatestTransactions("john");
        assertThat(transactionDtos).isNotNull();
        assertThat(transactionDtos).isNotEmpty();
    }

    @Test
    void givenNonExistingUsername_WhenFindPlayerLatestTransactions_ThrowPlayerNotFoundException() {
        when(playerService.findPlayerLatestTransactions("john")).thenThrow(new PlayerNotFoundException(String.format("Player %s not found", "john")));
        PlayerNotFoundException thrown = Assertions.assertThrows(PlayerNotFoundException.class, () -> {
            playerService.findPlayerLatestTransactions("john");
        });
        assertThat(String.format("Player %s not found", "john")).isEqualTo(thrown.getMessage());
    }
}