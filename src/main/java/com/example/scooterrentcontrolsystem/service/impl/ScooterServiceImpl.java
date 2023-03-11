package com.example.scooterrentcontrolsystem.service.impl;

import com.example.scooterrentcontrolsystem.controller.exception.EntityNotFoundByIdException;
import com.example.scooterrentcontrolsystem.dao.ScooterDao;
import com.example.scooterrentcontrolsystem.domain.model.RentalPoint;
import com.example.scooterrentcontrolsystem.domain.model.Scooter;
import com.example.scooterrentcontrolsystem.domain.model.ScooterModel;
import com.example.scooterrentcontrolsystem.service.ScooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ScooterServiceImpl extends AbstractServiceImpl<Scooter, ScooterDao> implements ScooterService {
    private final ScooterDao scooterDao;

    @Transactional
    @Override
    public void updateScooterRentalPoint(Scooter scooter, RentalPoint rentalPoint) {
        scooter.setRentalPoint(rentalPoint);
        scooterDao.update(scooter);
    }

    @Override
    public ScooterModel getScooterModelById(Long id) {
        return new ScooterModel();
    }
    @Override
    protected ScooterDao getDefaultDao() {
        return scooterDao;
    }

    @Override
    protected Class<Scooter> getDefaultEntityClass() {
        return Scooter.class;
    }
}
