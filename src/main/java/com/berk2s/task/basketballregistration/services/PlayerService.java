package com.berk2s.task.basketballregistration.services;

import com.berk2s.task.basketballregistration.web.models.PlayerDto;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> getPlayers();
}
