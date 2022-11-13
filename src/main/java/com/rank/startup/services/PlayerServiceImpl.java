package com.rank.startup.services;

import com.rank.startup.dto.PlayerDto;
import com.rank.startup.dto.TransactionDto;
import com.rank.startup.dto.TransactionResponseDto;
import com.rank.startup.entities.Player;
import com.rank.startup.entities.Transaction;
import com.rank.startup.enums.TransactionType;
import com.rank.startup.exceptions.PlayerNotFoundException;
import com.rank.startup.exceptions.WagerGreaterThanBalanceException;
import com.rank.startup.mappers.PlayerMapper;
import com.rank.startup.mappers.TransactionMapper;
import com.rank.startup.repository.PlayerRepository;
import com.rank.startup.repository.TransactionRepository;
import com.rank.startup.utils.Calculations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public PlayerDto findPlayer(Integer playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        Player player = playerOptional.orElseThrow(() -> new PlayerNotFoundException(String.format("Player %o not found", playerId)));
        return playerMapper.toDto(player);
    }

    @Override
    public TransactionResponseDto updateBalance(Integer playerId, TransactionDto transactionDto) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        Player player = playerOptional.orElseThrow(() -> new PlayerNotFoundException(String.format("Player %o not found", playerId)));

        if(transactionDto.getTransactionType() == TransactionType.WAGER
                && player.getBalance().compareTo(transactionDto.getAmount()) < 0) {
            throw new WagerGreaterThanBalanceException(String.format("Wager %.2f is greater than balance. Player balance is %.2f", transactionDto.getAmount(), player.getBalance()));
        }

        if(transactionDto.getTransactionType() == TransactionType.WIN) {
            player.setBalance(Calculations.addToBalance(player.getBalance(), transactionDto.getAmount()));
        } else {
            player.setBalance(Calculations.subtractBalance(player.getBalance(), transactionDto.getAmount()));
        }

        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transaction.setPlayer(player);

        playerRepository.save(player);
        transactionRepository.save(transaction);

        return new TransactionResponseDto(transaction.getId(), player.getBalance());
    }

    @Override
    public List<TransactionDto> findPlayerLatestTransactions(String username) {
        Optional<Player> playerOptional = playerRepository.findByUsernameIgnoreCase(username);
        Player player = playerOptional.orElseThrow(() -> new PlayerNotFoundException(String.format("Player %s not found", username)));

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        List<Transaction> transactions = playerRepository.findTransactionsByPlayer(player, pageRequest);
        return transactions.stream()
                .map(transaction -> transactionMapper.toDto(transaction))
                .collect(Collectors.toList());
    }
}
