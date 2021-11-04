package com.berk2s.task.basketballregistration.bootstrap;

import com.berk2s.task.basketballregistration.domain.Player;
import com.berk2s.task.basketballregistration.domain.PlayerPosition;
import com.berk2s.task.basketballregistration.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Profile("local")
@Component
public class DataLoader implements CommandLineRunner {

    private final PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
        Player player = new Player();
        player.setFirstName(RandomStringUtils.random(10, true, false));
        player.setLastName(RandomStringUtils.random(10, true, false));
        player.setPosition(PlayerPosition.CENTER);

        playerRepository.save(player);

        log.info("Initial player has been created");
    }
}
