package com.berk2s.task.basketballregistration.web.models;

import com.berk2s.task.basketballregistration.domain.PlayerPosition;
import lombok.Data;

@Data
public class PlayerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private PlayerPosition position;

}
