package com.example.scooterrentcontrolsystem.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class User2SubscriptionDto {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SubscriptionDto subscription;
}
