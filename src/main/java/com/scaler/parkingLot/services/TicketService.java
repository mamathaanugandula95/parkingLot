package com.scaler.parkingLot.services;

import com.scaler.parkingLot.Exceptions.GateNotFoundException;
import com.scaler.parkingLot.models.*;
import com.scaler.parkingLot.repositories.GateRepository;
import com.scaler.parkingLot.repositories.ParkingLotRepository;
import com.scaler.parkingLot.repositories.TicketRepository;
import com.scaler.parkingLot.repositories.VehicleRepository;
import com.scaler.parkingLot.strategies.SpotAssignmentStrategy;
import com.scaler.parkingLot.strategies.SpotAssignmentStrategyFactory;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

public class TicketService {

    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(String vehicleNumber,
                              String vehicleOwnerName,
                              VehicleType vehicleType,
                              Long gateId) throws GateNotFoundException {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        Optional<Gate> optionalGate = gateRepository.findByGateId(gateId);
        if (!optionalGate.isPresent())
            throw new GateNotFoundException();
        Gate gate = optionalGate.get();
        ticket.setGate(gate);

        Vehicle savedVehicle;
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        if(!optionalVehicle.isPresent()){
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleType(vehicleType);
            vehicle.setNumber(vehicleNumber);
            vehicle.setOwnerName(vehicleOwnerName);
            savedVehicle = vehicleRepository.saveVehicle(vehicle);
        } else {
            savedVehicle = optionalVehicle.get();
        }

        ticket.setVehicle(savedVehicle);
        ticket.setGeneratedBy(gate.getOperator());
        ticket.setNumber("Ticket No " + new Random().nextInt());

        SpotAssignmentStrategyType type= parkingLotRepository.findByGate(gate).getSpotAssignmentStrategyType();
        SpotAssignmentStrategy strategy = SpotAssignmentStrategyFactory.getSpotAssignmentStrategy(type);
        ticket.setParkingSpot(strategy.getSpot(gate, vehicleType));
        ticketRepository.saveTicket(ticket);
        return ticket;
    }
}
