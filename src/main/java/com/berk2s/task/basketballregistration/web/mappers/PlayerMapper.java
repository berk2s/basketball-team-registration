package com.berk2s.task.basketballregistration.web.mappers;

import com.berk2s.task.basketballregistration.domain.Player;
import com.berk2s.task.basketballregistration.web.models.PlayerDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PlayerMapper {

    List<PlayerDto> playerToPlayerDto(List<Player> players);

}
