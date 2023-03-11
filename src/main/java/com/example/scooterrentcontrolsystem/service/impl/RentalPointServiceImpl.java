package com.example.scooterrentcontrolsystem.service.impl;

import com.example.scooterrentcontrolsystem.dao.RentalPointDao;
import com.example.scooterrentcontrolsystem.domain.model.RentalPoint;
import com.example.scooterrentcontrolsystem.service.RentalPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RentalPointServiceImpl extends AbstractServiceImpl<RentalPoint, RentalPointDao> implements RentalPointService {
    private final RentalPointDao rentalPointDao;

    @Override
    protected RentalPointDao getDefaultDao() {
        return rentalPointDao;
    }

    @Override
    protected Class<RentalPoint> getDefaultEntityClass() {
        return RentalPoint.class;
    }
}
