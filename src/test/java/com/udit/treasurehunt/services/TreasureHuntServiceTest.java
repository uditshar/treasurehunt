package com.udit.treasurehunt.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udit.treasurehunt.exceptions.InvalidTreasureMap;
import com.udit.treasurehunt.exceptions.LoopDetectedException;
import com.udit.treasurehunt.models.TreasureMap;
import com.udit.treasurehunt.models.TreasureHuntResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class TreasureHuntServiceTest {

    private static final String REQUEST = "request/valid_request.json";
    private static final String LOOP_REQUEST = "request/loop_request.json";
    private static final String RESPONSE = "response/valid_response.json";

    private TreasureHuntService treasureHuntService ;

    @BeforeEach
    void init(){
        treasureHuntService = new TreasureHuntServiceImpl();
    }

    @Test
    void searchTreasure() throws IOException, LoopDetectedException, InvalidTreasureMap {
        File reqFile = new ClassPathResource(REQUEST).getFile();
        TreasureMap treasureMap = new ObjectMapper().readValue(reqFile, TreasureMap.class);
        File respFile = new ClassPathResource(RESPONSE).getFile();
        TreasureHuntResponse expectedResponse = new ObjectMapper().readValue(respFile, TreasureHuntResponse.class);
        TreasureHuntResponse actualResponse = treasureHuntService.searchTreasure(treasureMap);
        actualResponse.getNodesVisited().forEach(node -> node.setVisited(false));
        Assertions.assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void searchTreasure_loopFoundException() throws IOException {
        File reqFile = new ClassPathResource(LOOP_REQUEST).getFile();
        TreasureMap treasureMap = new ObjectMapper().readValue(reqFile, TreasureMap.class);
        Assertions.assertThrows(LoopDetectedException.class, () -> treasureHuntService.searchTreasure(treasureMap)) ;
    }
}
