package com.scaler.parkingLot.strategies;

import com.scaler.parkingLot.models.Gate;
import com.scaler.parkingLot.models.ParkingSpot;
import com.scaler.parkingLot.models.VehicleType;

public interface SpotAssignmentStrategy {
    ParkingSpot getSpot(Gate gate, VehicleType vehicleType);
}