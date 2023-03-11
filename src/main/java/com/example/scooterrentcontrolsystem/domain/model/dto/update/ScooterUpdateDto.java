package com.example.scooterrentcontrolsystem.domain.model.dto.update;

import com.example.scooterrentcontrolsystem.domain.model.enums.ScooterConditionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScooterUpdateDto {
    private ScooterConditionStatus status;
    private Double charge;
}
