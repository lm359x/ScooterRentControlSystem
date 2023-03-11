package com.example.scooterrentcontrolsystem.controller.mapper;

import com.example.scooterrentcontrolsystem.domain.model.ScooterModel;
import com.example.scooterrentcontrolsystem.domain.model.dto.ScooterModelDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ScooterModelMapper {
    private final ModelMapper modelMapper;

    public ScooterModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ScooterModelDto convertToDto(ScooterModel entity) {
        return modelMapper.map(entity, ScooterModelDto.class);
    }
}
