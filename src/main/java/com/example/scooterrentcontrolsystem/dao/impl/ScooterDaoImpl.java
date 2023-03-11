package com.example.scooterrentcontrolsystem.dao.impl;

import com.example.scooterrentcontrolsystem.dao.ScooterDao;
import com.example.scooterrentcontrolsystem.domain.model.Scooter;
import org.springframework.stereotype.Repository;

@Repository
public class ScooterDaoImpl extends AbstractDaoImpl<Scooter> implements ScooterDao {
    @Override
    protected Class<Scooter> daoEntityClass() {
        return Scooter.class;
    }
}
