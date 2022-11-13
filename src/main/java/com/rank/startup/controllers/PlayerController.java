package com.rank.startup.controllers;

import com.rank.startup.dto.PlayerDto;
import com.rank.startup.dto.TransactionDto;
import com.rank.startup.dto.TransactionResponseDto;
import com.rank.startup.services.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/{playerId}/balance")
    public ResponseEntity<PlayerDto> getBalance(@PathVariable(value = "playerId") Integer playerId){
        PlayerDto playerDto = playerService.findPlayer(playerId);
        return new ResponseEntity<>(playerDto, HttpStatus.OK);
    }

    @PostMapping("/{playerId}/balance")
    public ResponseEntity<TransactionResponseDto> updateBalance(@PathVariable(value = "playerId") Integer playerId, @Valid @RequestBody TransactionDto transactionDto){
        TransactionResponseDto transactionResponseDto = playerService.updateBalance(playerId, transactionDto);
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.OK);
    }
}
