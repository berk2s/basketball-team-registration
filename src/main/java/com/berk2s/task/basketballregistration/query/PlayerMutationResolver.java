package com.berk2s.task.basketballregistration.query;

import com.berk2s.task.basketballregistration.web.models.CreatePlayerDto;
import com.berk2s.task.basketballregistration.web.models.PlayerDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class PlayerMutationResolver implements GraphQLMutationResolver {

    public PlayerDto createPlayer(CreatePlayerDto createPlayerDto) {
        return null;
    }

    public Boolean deletePlayer(long id) {
        return null;
    }

}
