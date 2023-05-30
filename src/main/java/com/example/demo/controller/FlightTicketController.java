package com.example.demo.controller;

import com.example.demo.FlightBookingDto.FlightBookingRequest;
import com.example.demo.FlightBookingDto.FlightBookingAcknowledgement;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.flightService.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightTicketController {

    @Autowired
    private FlightBookingService service;

    @PostMapping("/bookFlightTicket")
    public FlightBookingAcknowledgement bookFlightTicket(@RequestBody FlightBookingRequest request)
            throws UserNotFoundException {
     return service.bookFlightTicket(request);
    }

}
