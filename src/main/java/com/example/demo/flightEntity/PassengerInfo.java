package com.example.demo.flightEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "passengerInfo")
public class PassengerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;
    @NotNull
    private String name;
    @Email
    private String email;
    private String source;
    private String Destination;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date travelDate;
    private String pickUpTime;
    private String arrivalTime;
    private double fare;
}
