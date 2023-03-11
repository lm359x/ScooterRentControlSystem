package com.example.scooterrentcontrolsystem.controller.mapper;


import com.example.scooterrentcontrolsystem.domain.model.Scooter;
import com.example.scooterrentcontrolsystem.domain.model.ScooterModel;
import com.example.scooterrentcontrolsystem.domain.model.dto.ScooterDto;
import com.example.scooterrentcontrolsystem.domain.model.dto.ScooterModelDto;
import com.example.scooterrentcontrolsystem.domain.model.dto.create.ScooterCreationDto;
import com.example.scooterrentcontrolsystem.service.ScooterService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ScooterMapper {

    private final ModelMapper modelMapper;
    private final Converter<ScooterModel, ScooterModelDto> scooterModelDtoConverter;
    private final Converter<Long, ScooterModel> scooterIdToScooterModelConverter;

    public ScooterMapper(ModelMapper modelMapper,
                         ScooterModelMapper scooterModelMapper,
                         ScooterService scooterService) {

        this.modelMapper = modelMapper;

        scooterModelDtoConverter = (src) -> scooterModelMapper.convertToDto(src.getSource());

        scooterIdToScooterModelConverter = (src) -> {
            Long id = src.getSource();
            return scooterService.getScooterModelById(id);
        };

        modelMapper.createTypeMap(Scooter.class, ScooterDto.class)
                .addMapping(scooter -> scooter.getRentalPoint().getId(), ScooterDto::setRentalPointId)
                .addMappings(mapper -> mapper
                        .using(scooterModelDtoConverter)
                        .map(Scooter::getModel, ScooterDto::setModel));

        modelMapper.createTypeMap(ScooterCreationDto.class, Scooter.class)
                .addMappings(mapper -> mapper
                        .using(scooterIdToScooterModelConverter)
                        .map(ScooterCreationDto::getModelId, Scooter::setModel))
                .addMappings(mapper -> mapper
                        .map((scooterCreationDto) -> null, Scooter::setId));
    }

    public ScooterDto convertToDto(Scooter entity) {
        return modelMapper.map(entity, ScooterDto.class);
    }

    public Scooter convertToScooter(ScooterCreationDto dto) {
        return modelMapper.map(dto, Scooter.class);
    }
}
