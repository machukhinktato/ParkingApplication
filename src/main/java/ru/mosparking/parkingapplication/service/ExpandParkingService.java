package ru.mosparking.parkingapplication.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mosparking.parkingapplication.dto.AdditionalParkingSpaceDto;
import ru.mosparking.parkingapplication.dto.ResponseDto;
import ru.mosparking.parkingapplication.model.Parking;
import ru.mosparking.parkingapplication.model.ParkingPlace;
import ru.mosparking.parkingapplication.repository.ParkingPlaceRepository;
import ru.mosparking.parkingapplication.repository.ParkingRepository;

import java.util.Optional;

/**
 * @author machukhinktato (Tarabrin Mikhail) 14/02/2022
 */
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExpandParkingService {
    ParkingPlaceRepository parkingPlaceRepository;

    ParkingRepository parkingRepository;

    @Autowired
    public ExpandParkingService(ParkingPlaceRepository parkingPlaceRepository, ParkingRepository parkingRepository) {
        this.parkingPlaceRepository = parkingPlaceRepository;
        this.parkingRepository = parkingRepository;
    }

    public ResponseDto increaseQuantityOfParkingPlaces(AdditionalParkingSpaceDto dto) {
        Optional<Parking> parkingOwner = parkingRepository.findFirstByNameEquals(dto.getName());
        if (parkingOwner.isPresent()) {
            Integer totalParkingSpace = parkingPlaceRepository.parkingTotalSpaceQuantity(parkingOwner.get().getId());
            if (dto.getQuantity() != null) {
                if (dto.getQuantity() > 0) {
                    for (int i = 0; i < dto.getQuantity(); i++) {
                        parkingPlaceRepository.save(new ParkingPlace(parkingOwner.get(), ++totalParkingSpace));
                    }
                    return new ResponseDto("ok", "parking places added");
                } else {
                    return new ResponseDto("error", "wrong data ");
                }
            } else {
                parkingPlaceRepository.save(new ParkingPlace(parkingOwner.get(), totalParkingSpace));
            }
            return new ResponseDto("ok", "parking place added");
        }
        return new ResponseDto("error", "wrong data ");
    }


}
