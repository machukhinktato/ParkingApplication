package ru.mosparking.parkingapplication;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.mosparking.parkingapplication.dto.AdditionalParkingSpaceDto;
import ru.mosparking.parkingapplication.dto.ParkingOrganisationDto;
import ru.mosparking.parkingapplication.repository.ParkingPlaceRepository;
import ru.mosparking.parkingapplication.repository.ParkingRepository;
import ru.mosparking.parkingapplication.service.ExpandParkingService;
import ru.mosparking.parkingapplication.service.OrganisationRegistrationService;

@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ParkingApplication implements CommandLineRunner {

    ParkingRepository parkingRepository;

    OrganisationRegistrationService registrationService;

    ExpandParkingService parkingService;

    @Autowired
    public ParkingApplication(ParkingRepository parkingRepository, OrganisationRegistrationService registrationService, ExpandParkingService parkingService) {
        this.parkingRepository = parkingRepository;
        this.registrationService = registrationService;
        this.parkingService = parkingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (parkingRepository.findAll().size() == 0){
            registrationService.addNewOrganisation(new ParkingOrganisationDto("moscow_parking"));
            parkingService.increaseQuantityOfParkingPlaces(new AdditionalParkingSpaceDto("moscow_parking", 10));
        };
    }

}
