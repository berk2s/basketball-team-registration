package com.berk2s.task.basketballregistration.query;

import com.berk2s.task.basketballregistration.services.PlayerService;
import com.berk2s.task.basketballregistration.web.models.CreatePlayerDto;
import com.berk2s.task.basketballregistration.web.models.PlayerDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@RequiredArgsConstructor
@Component
public class PlayerMutationResolver implements GraphQLMutationResolver {

    private final PlayerService playerService;

    public PlayerDto createPlayer(@Valid CreatePlayerDto createPlayerDto) {
        return playerService.createPlayer(createPlayerDto);
    }

    public Boolean deletePlayer(Long id) {
        return playerService.deletePlayer(id);
    }

}
