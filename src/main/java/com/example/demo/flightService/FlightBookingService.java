package com.example.demo.flightService;

import com.example.demo.FlightBookingDto.FlightBookingRequest;
import com.example.demo.FlightBookingDto.FlightBookingAcknowledgement;
import com.example.demo.flightEntity.PassengerInfo;
import com.example.demo.flightEntity.PaymentInfo;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PassengerInfoRepository;
import com.example.demo.repository.PaymentInfoRepository;
import com.example.demo.utils.PaymentUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class FlightBookingService {

    private static final Logger logger = LoggerFactory.getLogger(FlightBookingService.class);
    @Autowired private PassengerInfoRepository passengerInfoRepository;
    @Autowired private PaymentInfoRepository paymentInfoRepository;

    @Transactional

    public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request) throws UserNotFoundException {
        PassengerInfo passengerInfo = request.getPassengerInfo();
        logger.debug("Passenger info {}" + passengerInfo);
        passengerInfoRepository.save(passengerInfo);

        PaymentInfo paymentInfo=request.getPaymentInfo();
        PaymentUtils paymentUtils = new PaymentUtils();
        paymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), passengerInfo.getFare());

        //might get exception if exception occurs, it won't go down

        paymentInfo.setPassengerId(passengerInfo.getPid());
        paymentInfo.setAmount(passengerInfo.getFare());
        paymentInfoRepository.save(paymentInfo);
        logger.debug("Payment info"+ paymentInfo);
        if(passengerInfo!=null){
            return new FlightBookingAcknowledgement("SUCCESS", passengerInfo.getFare(), UUID.randomUUID().toString(),passengerInfo);
        }else {
            throw new UserNotFoundException("User not found" + passengerInfo);
        }
    }


}
