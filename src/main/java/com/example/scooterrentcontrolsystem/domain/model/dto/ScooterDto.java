package com.example.scooterrentcontrolsystem.domain.model.dto;

import com.example.scooterrentcontrolsystem.domain.model.enums.ScooterConditionStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScooterDto {

    private Long id;
    private ScooterModelDto model;
    private ScooterConditionStatus status;
    private Double charge;
    private Double mileage;

    private Long rentalPointId;
}
