package ru.mosparking.parkingapplication.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mosparking.parkingapplication.dto.ParkingOrganisationDto;
import ru.mosparking.parkingapplication.dto.ResponseDto;
import ru.mosparking.parkingapplication.model.Parking;
import ru.mosparking.parkingapplication.repository.ParkingRepository;

/**
 * @author machukhinktato (Tarabrin Mikhail) 14/02/2022
 */
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrganisationRegistrationService {

    ParkingRepository repository;

    @Autowired
    public OrganisationRegistrationService(ParkingRepository repository) {
        this.repository = repository;
    }

    public ResponseDto addNewOrganisation(ParkingOrganisationDto dto) {
        if (repository.findFirstByNameEquals(dto.getName()).isPresent()) {
            return new ResponseDto("error", "already exists");
        }
        repository.save(new Parking(dto.getName()));
        return new ResponseDto("ok", "registration complete");
    }
}
