package com.example.demo.FlightBookingDto;

import com.example.demo.flightEntity.PassengerInfo;
import com.example.demo.flightEntity.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingRequest {
    private PassengerInfo passengerInfo;

    private PaymentInfo paymentInfo;
}
