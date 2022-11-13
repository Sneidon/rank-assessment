package com.rank.startup.controllers;

import com.rank.startup.dto.TransactionsRequestDto;
import com.rank.startup.dto.TransactionDto;
import com.rank.startup.services.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/player/transactions")
    public ResponseEntity<List<TransactionDto>> updateBalance(@Valid @RequestBody TransactionsRequestDto listTransactionsRequestDto){
        List<TransactionDto> transactionDtos = playerService.findPlayerTransactions(listTransactionsRequestDto.getUsername());
        return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
    }
}
