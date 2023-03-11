package com.example.scooterrentcontrolsystem.domain.model.dto;

import com.example.scooterrentcontrolsystem.domain.model.Geolocation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RentalPointDto {

    private Long id;
    private Integer currentNumberOfScooters;

    private Geolocation geolocation;
    private Double distanceToClientInKm;

    private List<ScooterDto> scooters;
}
