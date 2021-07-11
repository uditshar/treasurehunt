package com.udit.treasurehunt.services;

import com.udit.treasurehunt.exceptions.LoopDetectedException;
import com.udit.treasurehunt.models.TreasureHuntResponse;
import com.udit.treasurehunt.models.TreasureMap;

public interface TreasureHuntService {
    public TreasureHuntResponse searchTreasure(TreasureMap map) throws LoopDetectedException;
}
