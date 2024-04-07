package com.scaler.parkingLot.controllers;

import com.scaler.parkingLot.dto.IssueTicketRequest;
import com.scaler.parkingLot.dto.IssueTicketResponse;
import com.scaler.parkingLot.dto.ResponseStatus;
import com.scaler.parkingLot.models.Ticket;
import com.scaler.parkingLot.services.TicketService;

public class TicketController {
    private TicketService ticketService;
    //Constructor
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    //Method
    public IssueTicketResponse issueTicket(IssueTicketRequest request){
        IssueTicketResponse response = new IssueTicketResponse();
        try{
            Ticket ticket = ticketService.issueTicket(
                    request.getVehicleNumber(), request.getVehicleOwner(),
                    request.getVehicleType(), request.getGateId()
            );
            response.setTicket(ticket);
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e){
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}