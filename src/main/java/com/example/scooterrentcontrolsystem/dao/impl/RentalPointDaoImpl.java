package com.example.scooterrentcontrolsystem.dao.impl;

import com.example.scooterrentcontrolsystem.dao.RentalPointDao;
import com.example.scooterrentcontrolsystem.domain.model.RentalPoint;
import org.springframework.stereotype.Repository;

@Repository
public class RentalPointDaoImpl extends AbstractDaoImpl<RentalPoint> implements RentalPointDao {
    @Override
    protected Class<RentalPoint> daoEntityClass() {
        return RentalPoint.class;
    }
}
