package com.berk2s.task.basketballregistration.domain;

import lombok.Getter;

@Getter
public enum PlayerPosition {
    POINT_GUARD("Point Guard"),
    SHOOTING_GUARD("Shooting Guard"),
    SMALL_FORWARD("Small Forward"),
    POWER_FORWARD("Power Forward"),
    CENTER("Center");

    final String normalizedName;

    PlayerPosition(String normalizedName) {
        this.normalizedName = normalizedName;
    }
}
