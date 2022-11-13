package com.rank.startup.mappers;

import com.rank.startup.dto.TransactionDto;
import com.rank.startup.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mappings( { @Mapping( source = "id", target = "transactionId" ) } )
    TransactionDto toDto(Transaction transaction);

    @Mappings( { @Mapping( source = "transactionId", target = "id" ) } )
    Transaction toEntity(TransactionDto transactionDto);
}