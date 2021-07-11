package com.udit.treasurehunt.services;

import com.udit.treasurehunt.exceptions.InvalidTreasureMap;
import com.udit.treasurehunt.exceptions.LoopDetectedException;
import com.udit.treasurehunt.models.Node;
import com.udit.treasurehunt.models.TreasureMap;
import com.udit.treasurehunt.models.TreasureHuntResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@Slf4j
public class TreasureHuntServiceImpl implements TreasureHuntService {
    @Override
    public TreasureHuntResponse searchTreasure(TreasureMap map) throws LoopDetectedException {
        log.info("Treasure hunt begun");
        var nodesVisited = new ArrayList<Node>();
        Node[][] matrix = this.createTreasureMapMatrix(map);
        Node treasureNode = null;
        Node startNode = matrix[1][1];
        for(int i=1; i<25 ;i++){
            nodesVisited.add(startNode);
            Node nextNode = this.getNextNode(startNode.getClueValue(), matrix);

            if(nextNode.isVisited())
                throw new LoopDetectedException();
            nextNode.setVisited(true);
            log.info( startNode.getRow() +","+ startNode.getColumn() +" NEXT NODE = " + nextNode);
            if(nextNode.getClueValue() == (nextNode.getRow()*10 +nextNode.getColumn())){
                log.info("Treasure Found! at node " + nextNode);
                treasureNode = nextNode;
                nodesVisited.add(treasureNode);
                break;
            }
            startNode = nextNode;
        }

        return Objects.isNull(treasureNode) ? new TreasureHuntResponse(nodesVisited,false, null) :
                new TreasureHuntResponse(nodesVisited,true, treasureNode);
    }

    private Node[][] createTreasureMapMatrix(TreasureMap map){
        var matrix = new Node[6][6];
        for(Node node: map.getNodes()){
            matrix[node.getRow()][node.getColumn()] = node;
        }
        return matrix;
    }

    private Node getNextNode(int clueValue, Node[][] matrix) {
        int column = clueValue % 10;
        int row = clueValue/10;
        return matrix[row][column];
    }
}
