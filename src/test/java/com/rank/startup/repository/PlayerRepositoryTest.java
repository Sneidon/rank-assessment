package com.rank.startup.repository;

import com.rank.startup.entities.Player;
import com.rank.startup.entities.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerRepositoryTest {
    @Mock
    PlayerRepository playerRepository;

    @Test
    void givenExistingUsername_WhenFindByUsernameIgnoreCase_ReturnPlayer() {
        when(playerRepository.findByUsernameIgnoreCase("john")).thenReturn(java.util.Optional.of(new Player()));
        assertThat(playerRepository.findByUsernameIgnoreCase("john")).isNotNull();
    }

    @Test
    void givenNonExistingUsername_WhenFindByUsernameIgnoreCase_ReturnNull() {
        when(playerRepository.findByUsernameIgnoreCase("john")).thenReturn(Optional.empty());
        assertThat(playerRepository.findByUsernameIgnoreCase("john")).isEmpty();
    }

    @Test
    void givenExistingPlayer_WhenFndTransactionsByPlayer_ReturnTransactions() {
        when(playerRepository.findTransactionsByPlayer(new Player(), Pageable.ofSize(10))).thenReturn(Arrays.asList(new Transaction()));
        assertThat(playerRepository.findTransactionsByPlayer(new Player(), Pageable.ofSize(10))).isNotEmpty();
    }

    @Test
    void givenNonExistingPlayer_WhenFndTransactionsByPlayer_ReturnEmptyList() {
        when(playerRepository.findTransactionsByPlayer(new Player(), Pageable.ofSize(10))).thenReturn(Arrays.asList());
        assertThat(playerRepository.findTransactionsByPlayer(new Player(), Pageable.ofSize(10))).isEmpty();
    }
}