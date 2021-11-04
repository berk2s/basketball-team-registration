package com.berk2s.task.basketballregistration.web.models;

import com.berk2s.task.basketballregistration.domain.PlayerPosition;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreatePlayerDto {

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotNull
    PlayerPosition position;

}
