package com.rank.startup.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rank.startup.dto.TransactionsRequestDto;
import com.rank.startup.repository.PlayerRepository;
import com.rank.startup.services.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminController.class)
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService playerService;
    @MockBean
    private PlayerRepository playerRepository;

    @Test
    void giveUsername_WhenListTransactions_ReturnHTTPStatusOkay() throws Exception {
        TransactionsRequestDto transactionDto = new TransactionsRequestDto("John");
        mockMvc.perform(post("/admin/player/transactions")
                .content(new ObjectMapper().writeValueAsString(transactionDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void giveEmptyUsername_WhenListTransactions_ReturnHTTPStatusOkay() throws Exception {
        mockMvc.perform(post("/admin/player/transactions")
                .content(new ObjectMapper().writeValueAsString(null))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}