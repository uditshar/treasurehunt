package com.udit.treasurehunt.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udit.treasurehunt.exceptions.LoopDetectedException;
import com.udit.treasurehunt.models.TreasureHuntResponse;
import com.udit.treasurehunt.models.TreasureMap;
import com.udit.treasurehunt.services.TreasureHuntService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TreasurehuntController.class)
public class TreasureHuntControllersTest {

    private static final String REQUEST = "request/valid_request.json";
    private static final String LOOP_REQUEST = "request/loop_request.json";
    private static final String RESPONSE = "response/valid_response.json";
    private static final String LOOP_RESPONSE = "response/loop_response.json";
    @Autowired
    private MockMvc mvc;
    @MockBean
    TreasureHuntService treasureHuntService;

    @Test
    public void search() throws Exception {
        File reqFile = new ClassPathResource(REQUEST).getFile();
        TreasureMap treasureMap = new ObjectMapper().readValue(reqFile, TreasureMap.class);
        File respFile = new ClassPathResource(RESPONSE).getFile();
        TreasureHuntResponse expectedResponse = new ObjectMapper().readValue(respFile, TreasureHuntResponse.class);

        Mockito.when(treasureHuntService.searchTreasure(treasureMap)).thenReturn(expectedResponse);
        MockHttpServletResponse res = mvc.perform(MockMvcRequestBuilders.post("/treasurehunt/map")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(treasureMap))
        ).andReturn().getResponse();
        Assertions.assertEquals(res.getStatus(),200);
    }

    @Test
    public void search_loop() throws Exception {
        File reqFile = new ClassPathResource(LOOP_REQUEST).getFile();
        TreasureMap treasureMap = new ObjectMapper().readValue(reqFile, TreasureMap.class);
        File respFile = new ClassPathResource(LOOP_RESPONSE).getFile();
        Mockito.when(treasureHuntService.searchTreasure(treasureMap)).thenThrow(new LoopDetectedException());
        MockHttpServletResponse res = mvc.perform(MockMvcRequestBuilders.post("/treasurehunt/map")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(treasureMap))
        ).andReturn().getResponse();
        Assertions.assertEquals(res.getStatus(),400);
    }
}
