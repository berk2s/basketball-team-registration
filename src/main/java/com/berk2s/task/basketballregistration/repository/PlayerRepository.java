package com.berk2s.task.basketballregistration.repository;

import com.berk2s.task.basketballregistration.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
