package com.scaler.parkingLot.strategies;

import com.scaler.parkingLot.models.SpotAssignmentStrategyType;

public class SpotAssignmentStrategyFactory {

    public static SpotAssignmentStrategy getSpotAssignmentStrategy(SpotAssignmentStrategyType type){
        return new NearestSpotAssignmentStrategy();
    }
}
