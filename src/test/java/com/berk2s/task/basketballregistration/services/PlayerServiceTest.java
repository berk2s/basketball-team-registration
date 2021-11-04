package com.berk2s.task.basketballregistration.services;

import com.berk2s.task.basketballregistration.domain.Player;
import com.berk2s.task.basketballregistration.repository.PlayerRepository;
import com.berk2s.task.basketballregistration.services.impl.PlayerServiceImpl;
import com.berk2s.task.basketballregistration.web.mappers.PlayerMapper;
import com.berk2s.task.basketballregistration.web.models.PlayerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    PlayerRepository playerRepository;

    @Spy
    private final PlayerMapper playerMapper = Mappers.getMapper(PlayerMapper.class);

    @InjectMocks
    PlayerServiceImpl playerService;


    @DisplayName("Test Get Players")
    @Test
    void shouldGetPlayersSuccessfully() {
        Player player1 = new Player();
        player1.setId(1L);

        Player player2 = new Player();
        player2.setId(2L);

        var mockedPlayers = List.of(player1, player2);

        when(playerRepository.findAll()).thenReturn(mockedPlayers);

        List<PlayerDto> returnedPlayers = playerService.getPlayers();

        assertThat(returnedPlayers.size())
                .isEqualTo(mockedPlayers.size());

        verify(playerRepository, times(1)).findAll();
    }

}