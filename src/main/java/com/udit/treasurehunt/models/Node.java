package com.udit.treasurehunt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Node {

  @Max(value = 5, message = "row value must be between 1 and 5")
  @Min(value = 1, message = "row value must be between 1 and 5")
  int row;

  @Max(value = 5, message = "column value must be between 1 and 5")
  @Min(value = 1, message = "column value must be between 1 and 5")
  int column;

  @Max(value = 55, message = "clue value must be between 11 and 55")
  @Min(value = 11, message = "clue value must be between 11 and 55")
  int clueValue;

  @EqualsAndHashCode.Exclude
  @JsonIgnore
  boolean visited;

}
