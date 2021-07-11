package com.udit.treasurehunt.models;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class TreasureMap {
    @Valid
    @NotEmpty(message = "cannot be empty")
    @Size(min = 25, max = 25, message = "the treasure map should contain 25 clue values")
    Node[] nodes;
}
