package com.example.scooterrentcontrolsystem.service;

import com.example.scooterrentcontrolsystem.domain.model.RentalPoint;
import com.example.scooterrentcontrolsystem.domain.model.Scooter;
import com.example.scooterrentcontrolsystem.domain.model.ScooterModel;

public interface ScooterService extends AbstractService<Scooter>{
    void updateScooterRentalPoint(Scooter scooter, RentalPoint rentalPoint);
    ScooterModel getScooterModelById(Long id);
}
