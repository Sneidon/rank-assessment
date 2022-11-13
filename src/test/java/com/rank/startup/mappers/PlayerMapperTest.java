package com.rank.startup.mappers;

import com.rank.startup.dto.PlayerDto;
import com.rank.startup.entities.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = { PlayerMapperImpl.class, TransactionMapperImpl.class })
@ExtendWith(MockitoExtension.class)
class PlayerMapperTest {
    @InjectMocks
    PlayerMapperImpl playerMapper;

    @Test
    void givenModelWithEmptyTransactions_WhenToDto_ReturnPlayerDto() {
        Player player = new Player();
        player.setId(1);
        player.setBalance(BigDecimal.TEN);

        assertThat(playerMapper.toDto(player)).isExactlyInstanceOf(PlayerDto.class);
        assertThat(playerMapper.toDto(player).getPlayerId()).isEqualTo(1);
        assertThat(playerMapper.toDto(player).getBalance()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void givenNullModel_WhenToDto_ReturnNull() {
        assertThat(playerMapper.toDto(null)).isNull();
    }
}