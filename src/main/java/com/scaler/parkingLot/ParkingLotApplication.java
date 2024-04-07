package com.scaler.parkingLot;

import com.scaler.parkingLot.controllers.TicketController;
import com.scaler.parkingLot.repositories.GateRepository;
import com.scaler.parkingLot.repositories.ParkingLotRepository;
import com.scaler.parkingLot.repositories.TicketRepository;
import com.scaler.parkingLot.repositories.VehicleRepository;
import com.scaler.parkingLot.services.TicketService;

public class ParkingLotApplication {
    public static void main(String[] args) {

        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        TicketService ticketService = new TicketService(gateRepository,
                vehicleRepository, parkingLotRepository, ticketRepository);

        TicketController ticketController = new TicketController(ticketService);

        System.out.println("parkingLot");


    }
}