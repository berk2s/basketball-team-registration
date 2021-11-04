package com.berk2s.task.basketballregistration.query;

import com.berk2s.task.basketballregistration.domain.Player;
import com.berk2s.task.basketballregistration.domain.PlayerPosition;
import com.berk2s.task.basketballregistration.repository.PlayerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.LinkedHashMap;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlayerMutationResolverTest {

    private final static String CREATING_REQUEST_PATH = "create_player.graphql";
    private final static String DELETING_REQUEST_PATH = "delete_player.graphql";
    private final static String INVALID_DELETING_REQUEST_PATH = "invalid_delete_player.graphql";

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PlayerRepository playerRepository;

    @DisplayName("Creating Player")
    @Nested
    class CreatingPlayer {

        @DisplayName("Create Player Successfully")
        @Test
        void createPlayerSuccessfully() throws IOException {
            GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(CREATING_REQUEST_PATH);

            String content = graphQLResponse.getRawResponse().getBody();

            DocumentContext context = JsonPath.parse(content);
            LinkedHashMap jsonPathCreatedPlayer = context.read("$['data']['createPlayer']");

            assertThat(graphQLResponse.getStatusCode())
                    .isEqualTo(HttpStatus.OK);

            assertThat(jsonPathCreatedPlayer.get("id"))
                    .isNotNull();

            assertThat(jsonPathCreatedPlayer.get("firstName"))
                    .isEqualTo("Berk");

            assertThat(jsonPathCreatedPlayer.get("lastName"))
                    .isEqualTo("Topcu");

            assertThat(jsonPathCreatedPlayer.get("position"))
                    .isEqualTo("CENTER");
        }

        @DisplayName("Create Player Capacity Full Error")
        @Test
        void createPlayerCapacityFullError() throws IOException {
            for (int i = 0; i < 5; i++) {
                Player player = new Player();
                player.setFirstName(RandomStringUtils.random(10, true, false));
                player.setLastName(RandomStringUtils.random(10, true, false));
                player.setPosition(PlayerPosition.CENTER);

                playerRepository.save(player);
            }

            GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(CREATING_REQUEST_PATH);

            String content = graphQLResponse.getRawResponse().getBody();

            DocumentContext context = JsonPath.parse(content);
            String jsonPathError = context.read("$['errors'][0]['message']");

            assertThat(jsonPathError)
                    .isEqualTo("Exception while fetching data (/createPlayer) : The player capacity is full");
        }

    }

    @DisplayName("Deleting Player")
    @Nested
    class DeletingPlayer {

        Player player;

        @BeforeEach
        void setUp() {
            player = new Player();
            player.setFirstName(RandomStringUtils.random(10, true, false));
            player.setLastName(RandomStringUtils.random(10, true, false));
            player.setPosition(PlayerPosition.CENTER);

            playerRepository.save(player);
        }

        @DisplayName("Delete Player Successfully")
        @Test
        void deletePlayerSuccessfully() throws IOException {
            GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(DELETING_REQUEST_PATH);

            String content = graphQLResponse.getRawResponse().getBody();

            DocumentContext context = JsonPath.parse(content);
            Boolean jsonPathStatus = context.read("$['data']['deletePlayer']");

            assertThat(graphQLResponse.getStatusCode())
                    .isEqualTo(HttpStatus.OK);

            assertThat(jsonPathStatus)
                    .isEqualTo(true);

        }

        @DisplayName("Delete Player Invalid Id Error")
        @Test
        void deletePlayerInvalidIdError() throws IOException {
            GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(INVALID_DELETING_REQUEST_PATH);

            String content = graphQLResponse.getRawResponse().getBody();

            DocumentContext context = JsonPath.parse(content);
            String jsonPathError = context.read("$['errors'][0]['message']");

            assertThat(jsonPathError)
                    .isEqualTo("Exception while fetching data (/deletePlayer) : Invalid player id");
        }

    }

}