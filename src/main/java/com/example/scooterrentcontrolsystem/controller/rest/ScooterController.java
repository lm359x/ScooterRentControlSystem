package com.example.scooterrentcontrolsystem.controller.rest;

import com.example.scooterrentcontrolsystem.controller.mapper.ScooterMapper;
import com.example.scooterrentcontrolsystem.domain.model.RentalPoint;
import com.example.scooterrentcontrolsystem.domain.model.Scooter;
import com.example.scooterrentcontrolsystem.domain.model.ScooterModel;
import com.example.scooterrentcontrolsystem.domain.model.dto.ScooterDto;
import com.example.scooterrentcontrolsystem.domain.model.dto.create.ScooterCreationDto;
import com.example.scooterrentcontrolsystem.domain.model.dto.update.ScooterUpdateDto;
import com.example.scooterrentcontrolsystem.service.RentalPointService;
import com.example.scooterrentcontrolsystem.service.ScooterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/v1/scooters", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ScooterController {
    private final ScooterService scooterService;
    private final RentalPointService rentalPointService;
    private final ScooterMapper scooterMapper;


    @GetMapping
    public List<ScooterDto> getAll(@RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
                                   @RequestParam(value = "asc", defaultValue = BooleanUtils.TRUE, required = false) boolean asc,
                                   @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit) {

        List<Scooter> scooters = scooterService.getAll(new HashMap<>(), orderBy, asc, limit);
        return scooters.stream().map(scooterMapper::convertToDto).collect(toList());
    }

    @GetMapping(value = "/{id}")
    public ScooterDto getById(@PathVariable("id") Long id) {
        Scooter scooter = scooterService.getById(id);
        return scooterMapper.convertToDto(scooter);
    }

    @PostMapping
    public void createScooter(@RequestBody ScooterCreationDto scooterCreationDto) {
        Scooter scooter = scooterMapper.convertToScooter(scooterCreationDto);
        scooterService.create(scooter);
    }

    @PatchMapping(value = "/{id}")
    public void updateScooter(@PathVariable("id") Long id, @RequestBody ScooterUpdateDto updateDto) {
        Scooter scooter = scooterService.getById(id);
        scooterService.updateEntityFromDto(scooter, updateDto, Scooter.class);
        scooterService.update(scooter);
    }

    @PatchMapping(value = "/{id}", params = {"rental-point-id"})
    public void updateScooterRentalPoint(@PathVariable("id") Long id,
                                         @RequestParam(value = "rental-point-id") Long rentalPointId) {
        Scooter scooter = scooterService.getById(id);
        RentalPoint rentalPoint = rentalPointService.getById(rentalPointId);
        scooterService.updateScooterRentalPoint(scooter, rentalPoint);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteScooter(@PathVariable("id") Long id) {
        scooterService.deleteById(id);
    }
}
