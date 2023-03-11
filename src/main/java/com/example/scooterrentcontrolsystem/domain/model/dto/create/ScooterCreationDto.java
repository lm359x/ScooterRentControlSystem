package com.example.scooterrentcontrolsystem.domain.model.dto.create;

import com.example.scooterrentcontrolsystem.domain.model.enums.ScooterConditionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScooterCreationDto {
    private Long modelId;
    private ScooterConditionStatus status = ScooterConditionStatus.UNAVAILABLE;
    private Double charge = 0d;
    private Double mileage = 0d;
}
