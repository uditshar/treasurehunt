package com.udit.treasurehunt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreasureHuntResponse {
    List<Node> nodesVisited;
    boolean treasureFound;
    Node treasureNode;
}
