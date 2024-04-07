package com.scaler.parkingLot.repositories;

import com.scaler.parkingLot.models.Gate;
import com.scaler.parkingLot.models.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {

    Map<Long, ParkingLot> parkingLots = new HashMap<>();

    public ParkingLot findByGate(Gate gate){

        for (ParkingLot parkingLot: parkingLots.values()){
            if(parkingLot.getGates().contains(gate)){
                return parkingLot;
            }
        }
        return null;
    }
}