package com.berk2s.task.basketballregistration.query;


import com.berk2s.task.basketballregistration.BasketballRegistrationApplication;
import com.berk2s.task.basketballregistration.domain.Player;
import com.berk2s.task.basketballregistration.domain.PlayerPosition;
import com.berk2s.task.basketballregistration.repository.PlayerRepository;
import com.berk2s.task.basketballregistration.web.models.PlayerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlayerListingQueryResolverTest {

    private final static String REQUEST_PATH = "list_players.graphql";

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ObjectMapper objectMapper;

    Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
        player.setFirstName(RandomStringUtils.random(10, true, false));
        player.setLastName(RandomStringUtils.random(10, true, false));
        player.setPosition(PlayerPosition.CENTER);

        playerRepository.save(player);
    }

    @DisplayName("Players Return Successfully")
    @Test
    void playersReturnsSuccessfully() throws IOException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(REQUEST_PATH);

        String content = graphQLResponse.getRawResponse().getBody();

        DocumentContext context = JsonPath.parse(content);
        List<LinkedHashMap> jsonPathPlayers = context.read("$['data']['getPlayers'][*]");

        assertThat(graphQLResponse.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(Integer.parseInt(jsonPathPlayers.get(0).get("id").toString()))
                .isEqualTo(player.getId().intValue());
    }

}