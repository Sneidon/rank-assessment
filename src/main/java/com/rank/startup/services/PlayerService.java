package com.rank.startup.services;

import com.rank.startup.dto.PlayerDto;
import com.rank.startup.dto.TransactionDto;
import com.rank.startup.dto.TransactionResponseDto;

import java.util.List;

public interface PlayerService {
    PlayerDto findPlayer(Integer playerId);
    List<TransactionDto> findPlayerLatestTransactions(String username);
    TransactionResponseDto updateBalance(Integer playerId, TransactionDto transactionDto);
}
