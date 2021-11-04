package com.berk2s.task.basketballregistration.services.impl;

import com.berk2s.task.basketballregistration.domain.Player;
import com.berk2s.task.basketballregistration.repository.PlayerRepository;
import com.berk2s.task.basketballregistration.services.PlayerService;
import com.berk2s.task.basketballregistration.web.exceptions.CapacityFullException;
import com.berk2s.task.basketballregistration.web.exceptions.InvalidPlayerIdException;
import com.berk2s.task.basketballregistration.web.mappers.PlayerMapper;
import com.berk2s.task.basketballregistration.web.models.CreatePlayerDto;
import com.berk2s.task.basketballregistration.web.models.PlayerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Override
    public List<PlayerDto> getPlayers() {
        log.info("All players in the system are listing");
        return playerMapper.playerToPlayerDto(playerRepository.findAll());
    }

    @Override
    public PlayerDto createPlayer(CreatePlayerDto createPlayerDto) {
        if (playerRepository.findAll().size() == 5) {
            log.warn("The player capacity is full");
            throw new CapacityFullException("The player capacity is full");
        }

        Player player = playerMapper.createPlayerToPlayer(createPlayerDto);

        return playerMapper.playerToPlayerDto(playerRepository.save(player));
    }

    @Override
    public Boolean deletePlayer(Long id) {
        if (id == null || !playerRepository.existsById(id)) {
            log.warn("Invalid player id");
            throw new InvalidPlayerIdException("Invalid player id");
        }

        playerRepository.deleteById(id);

        log.info("Player with given id has been deleted successfully [playerId: {}]", id);

        return true;
    }
}
