package ru.mosparking.parkingapplication.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class ControlParkingFreeSpaceService {
    ParkingPlaceRepository parkingPlaceRepository;

    ParkingRepository parkingRepository;

    @Autowired
    public ControlParkingFreeSpaceService(ParkingPlaceRepository parkingPlaceRepository, ParkingRepository parkingRepository) {
        this.parkingPlaceRepository = parkingPlaceRepository;
        this.parkingRepository = parkingRepository;
    }

    public ResponseDto parkingControlPanel(String name, String action) {
        Optional<Parking> organisation = parkingRepository.findFirstByNameEquals(name);
        if (organisation.isPresent()) {
            Integer freeSpaceCount = parkingPlaceRepository.countFreeSpace(organisation.get().getId());
            if (action.equals("status")) {
                    return new ResponseDto("ok", freeSpaceCount.toString());
            }
            return gates(action, freeSpaceCount, organisation.get().getId());
        }
        return new ResponseDto("rejected", "no such organisation registered");
    }

    private ResponseDto gates(String action, Integer parkingPlacesAvailable, Long parkingId) {
        if (action.equals("openentrancegate") || action.equals("openexitgate")){
            if (action.equals("openentrancegate") && parkingPlacesAvailable > 0){
                ParkingPlace parkingPlace = parkingPlaceRepository.findFirstByIsFreeIsTrueAndParkingId(parkingId);
                parkingPlace.setIsFree(Boolean.FALSE);
                parkingPlaceRepository.save(parkingPlace);
                return new ResponseDto("ok", "open the gate");
            } else if (action.equals("openentrancegate") && parkingPlacesAvailable == 0){
                return new ResponseDto("rejected", "there is no possibility to park a vehicle");
            } else if (action.equals("openexitgate")){
                ParkingPlace parkingPlace = parkingPlaceRepository.findFirstByIsFreeIsFalseAndParkingId(parkingId);
                parkingPlace.setIsFree(Boolean.TRUE);
                parkingPlaceRepository.save(parkingPlace);
                return new ResponseDto("ok", "open the gate");
            }
        }
        return new ResponseDto("error", "wrong query parameter, please check the documentation");
    }

}
