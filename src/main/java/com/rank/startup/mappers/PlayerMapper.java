package com.rank.startup.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.rank.startup.dto.PlayerDto;
import com.rank.startup.entities.Player;

@Mapper(componentModel = "spring",uses = TransactionMapper.class)
public interface PlayerMapper {
    @Mappings( { @Mapping( source = "id", target = "playerId" ) } )
    PlayerDto toDto(Player player);
}
