package com.berk2s.task.basketballregistration.web.mappers;

import com.berk2s.task.basketballregistration.domain.Player;
import com.berk2s.task.basketballregistration.web.models.CreatePlayerDto;
import com.berk2s.task.basketballregistration.web.models.PlayerDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PlayerMapper {

    PlayerDto playerToPlayerDto(Player player);

    List<PlayerDto> playerToPlayerDto(List<Player> players);

    Player createPlayerToPlayer(CreatePlayerDto createPlayerDto);

}
