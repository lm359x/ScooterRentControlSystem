package com.example.scooterrentcontrolsystem.domain.model.dto;

import com.example.scooterrentcontrolsystem.domain.model.enums.RideStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Setter
@Getter
public class RideDto {

    private Long id;
    private UserDto userDto;
    private ScooterDto scooterDto;

    private RideStatus status;
    private Double priceTotal;
    private RentalPointDto startRentalPointDto;
    private RentalPointDto endRentalPointDto;
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimestamp;

    private Double rideMileage;
    private Duration rideDuration;
}
