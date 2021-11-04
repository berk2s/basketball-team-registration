package com.berk2s.task.basketballregistration.services.impl;

import com.berk2s.task.basketballregistration.repository.PlayerRepository;
import com.berk2s.task.basketballregistration.services.PlayerService;
import com.berk2s.task.basketballregistration.web.mappers.PlayerMapper;
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
}
