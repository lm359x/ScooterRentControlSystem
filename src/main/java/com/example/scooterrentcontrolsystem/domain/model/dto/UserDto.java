package com.example.scooterrentcontrolsystem.domain.model.dto;

import com.example.scooterrentcontrolsystem.domain.model.enums.Role;
import com.example.scooterrentcontrolsystem.domain.model.enums.UserAccountStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserDto {

    private Long id;
    private Role role;

    private UserAccountStatus status;
    private String name;
    private String phone;
    private LocalDate dateOfBirth;

    private TariffDto tariff;
    private User2SubscriptionDto user2Subscription;
}
