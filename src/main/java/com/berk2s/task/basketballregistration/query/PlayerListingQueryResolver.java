package com.berk2s.task.basketballregistration.query;

import com.berk2s.task.basketballregistration.services.PlayerService;
import com.berk2s.task.basketballregistration.web.models.PlayerDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PlayerListingQueryResolver implements GraphQLQueryResolver {

    private final PlayerService playerService;

    public List<PlayerDto> getPlayers() {
        return playerService.getPlayers();
    }

}
