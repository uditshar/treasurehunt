package com.udit.treasurehunt.controllers;

import com.udit.treasurehunt.exceptions.LoopDetectedException;
import com.udit.treasurehunt.models.TreasureMap;
import com.udit.treasurehunt.models.TreasureHuntResponse;
import com.udit.treasurehunt.services.TreasureHuntService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RequestMapping(value = "/treasurehunt/")
@RestController
public class TreasurehuntController {

    private final TreasureHuntService treasureHuntService;
    @PostMapping("map")
    public TreasureHuntResponse treasureMap(@Valid @RequestBody TreasureMap map) throws LoopDetectedException {
        return treasureHuntService.searchTreasure(map);
    }
}
