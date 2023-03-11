package com.example.scooterrentcontrolsystem.controller.rest;

import com.example.scooterrentcontrolsystem.controller.mapper.RentalPointMapper;
import com.example.scooterrentcontrolsystem.domain.model.RentalPoint;
import com.example.scooterrentcontrolsystem.domain.model.dto.RentalPointDto;
import com.example.scooterrentcontrolsystem.domain.model.dto.create.RentalPointCreationDto;
import com.example.scooterrentcontrolsystem.service.RentalPointService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RequestMapping(value = "/v1/rental-points", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RentalPointController {
    private final RentalPointMapper rentalPointMapper;
    private final RentalPointService rentalPointService;

    @GetMapping
    public List<RentalPointDto> getAll(@RequestParam(value = "asc", defaultValue = BooleanUtils.TRUE, required = false) boolean asc,
                                       @RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
                                       @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit,
                                       @RequestParam(value = "countryCode", required = false) String countryCode,
                                       @RequestParam(value = "countryName", required = false) String countryName,
                                       @RequestParam(value = "county", required = false) String county,
                                       @RequestParam(value = "city", required = false) String city,
                                       @RequestParam(value = "district", required = false) String district,
                                       @RequestParam(value = "street", required = false) String street,
                                       @RequestParam(value = "houseNumber", required = false) String houseNumber,
                                       @RequestParam(value = "description", required = false) String description) {
        Map<String, Object> selectParameters = new HashMap<>() {{
            put("countryCode", countryCode);
            put("countryName", countryName);
            put("county", county);
            put("city", city);
            put("district", district);
            put("street", street);
            put("houseNumber", houseNumber);
            put("description", description);
        }};
        return rentalPointService.getAll(selectParameters,orderBy,asc,10).stream().map(rentalPointMapper::convertToDto).collect(toList());
    }
    @GetMapping(value = "/{id}")
    public RentalPointDto getById(@PathVariable("id") Long id) {
        RentalPoint rentalPoint = rentalPointService.getById(id);
        return rentalPointMapper.convertToDto(rentalPoint);
    }
    @PostMapping
    public void createRentalPoint(@RequestBody RentalPointCreationDto rentalPointCreationDto) {
        RentalPoint rentalPoint = rentalPointMapper.convertToRentalPoint(rentalPointCreationDto);
        rentalPointService.create(rentalPoint);
    }
    @DeleteMapping(value = "/{id}")
    public void deleteRentalPoint(@PathVariable("id") Long id) {
        rentalPointService.deleteById(id);
    }

}
