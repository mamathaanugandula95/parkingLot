package com.scaler.parkingLot.strategies;

import com.scaler.parkingLot.models.*;
import com.scaler.parkingLot.repositories.ParkingLotRepository;

public class NearestSpotAssignmentStrategy implements SpotAssignmentStrategy{

    ParkingLotRepository parkingLotRepository;

    @Override
    public ParkingSpot getSpot(Gate gate, VehicleType vehicleType) {
        ParkingLot parkingLot = parkingLotRepository.findByGate(gate);

        for(ParkingFloor floor: parkingLot.getFloors()){
            for(ParkingSpot parkingSpot: floor.getParkingSpots()){
                if(parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.EMPTY) && parkingSpot.getSupportedVehicleTypes().contains(vehicleType))
                    return parkingSpot;
            }
        }
        return null;
    }
}
