package ru.mosparking.parkingapplication.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mosparking.parkingapplication.dto.ParkingOrganisationDto;
import ru.mosparking.parkingapplication.dto.AdditionalParkingSpaceDto;
import ru.mosparking.parkingapplication.dto.ResponseDto;
import ru.mosparking.parkingapplication.service.ControlParkingFreeSpaceService;
import ru.mosparking.parkingapplication.service.ExpandParkingService;
import ru.mosparking.parkingapplication.service.OrganisationRegistrationService;

import java.util.Locale;

/**
 * @author machukhinktato (Tarabrin Mikhail) 14/02/2022
 */

@RestController
@RequestMapping("/api/parking")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ParkingFreeSpaceController {

    ExpandParkingService parking;

    OrganisationRegistrationService registration;

    ControlParkingFreeSpaceService parkingControl;

    @Autowired
    public ParkingFreeSpaceController(ExpandParkingService parking, OrganisationRegistrationService registration, ControlParkingFreeSpaceService parkingControl) {
        this.parking = parking;
        this.registration = registration;
        this.parkingControl = parkingControl;
    }

    @GetMapping
    public ResponseDto getFreeSpace(@RequestParam("name") String name, @RequestParam("action") String action) {
        return parkingControl.parkingControlPanel(name.toLowerCase(Locale.ROOT), action.toLowerCase(Locale.ROOT));
    }

    @PostMapping("/add")
    public ResponseDto add(@RequestBody AdditionalParkingSpaceDto dto) {
        return parking.increaseQuantityOfParkingPlaces(dto);
    }

    @PostMapping("/registration/new")
    public ResponseDto registerNewOrganisation(@RequestBody ParkingOrganisationDto dto) {
        return registration.addNewOrganisation(dto);
    }
}
