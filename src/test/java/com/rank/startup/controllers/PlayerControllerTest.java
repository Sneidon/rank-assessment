package com.rank.startup.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rank.startup.dto.TransactionDto;
import com.rank.startup.enums.TransactionType;
import com.rank.startup.repository.PlayerRepository;
import com.rank.startup.services.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlayerController.class)
class PlayerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService playerService;
    @MockBean
    private PlayerRepository playerRepository;

    @Test
    void givenPlayerId_WhenGetBalance_ReturnHTTPStatusOkay() throws Exception {
        mockMvc.perform(get("/player/1/balance"))
                .andExpect(status().isOk());
    }

    @Test
    void givePlayerIdAndTransactionDto_WhenPostBalance_ReturnHTTPStatusOkay() throws Exception {
        TransactionDto transactionDto = new TransactionDto(1, TransactionType.WAGER, BigDecimal.TEN);
        mockMvc.perform(post("/player/1/balance")
                .content(new ObjectMapper().writeValueAsString(transactionDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givePlayerIdAndEmptyTransactionDto_WhenPostBalance_ReturnHTTPStatusBad() throws Exception {
        mockMvc.perform(post("/player/1/balance")
                .content(new ObjectMapper().writeValueAsString(null))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}