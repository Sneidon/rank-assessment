package com.rank.startup.mappers;

import com.rank.startup.dto.TransactionDto;
import com.rank.startup.entities.Transaction;
import com.rank.startup.enums.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = { TransactionMapperImpl.class })
@ExtendWith(MockitoExtension.class)
class TransactionMapperTest {
    @InjectMocks
    TransactionMapperImpl transactionMapper;


    @Test
    void givenModel_WhenToDto_ReturnTransactionDto() {
        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setTransactionType(TransactionType.WAGER);
        transaction.setAmount(BigDecimal.TEN);

        assertThat(transactionMapper.toDto(transaction)).isExactlyInstanceOf(TransactionDto.class);
        assertThat(transactionMapper.toDto(transaction).getTransactionId()).isEqualTo(1);
        assertThat(transactionMapper.toDto(transaction).getTransactionType()).isEqualTo(TransactionType.WAGER);
        assertThat(transactionMapper.toDto(transaction).getAmount()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void givenNullModel_WhenToDto_ReturnNull() {
        assertThat(transactionMapper.toDto(null)).isNull();
    }

}