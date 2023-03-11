package com.example.scooterrentcontrolsystem.domain.model.dto.create;

import com.example.scooterrentcontrolsystem.domain.model.Geolocation;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RentalPointCreationDto {
    private Geolocation geolocation;
}
