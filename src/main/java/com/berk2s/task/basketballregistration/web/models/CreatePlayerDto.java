package com.berk2s.task.basketballregistration.web.models;

import com.berk2s.task.basketballregistration.domain.PlayerPosition;
import lombok.Data;

@Data
public class CreatePlayerDto {

    String firstName;

    String lastName;

    PlayerPosition position;

}
