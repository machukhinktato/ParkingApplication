package ru.mosparking.parkingapplication.utils;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mosparking.parkingapplication.controller.ParkingFreeSpaceController;
import ru.mosparking.parkingapplication.dto.AdditionalParkingSpaceDto;
import ru.mosparking.parkingapplication.dto.ResponseDto;
import ru.mosparking.parkingapplication.model.Parking;
import ru.mosparking.parkingapplication.model.ParkingPlace;
import ru.mosparking.parkingapplication.repository.ParkingPlaceRepository;
import ru.mosparking.parkingapplication.repository.ParkingRepository;
import ru.mosparking.parkingapplication.service.ControlParkingFreeSpaceService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author machukhinktato (Tarabrin Mikhail) 15/02/2022
 */
@ExtendWith(MockitoExtension.class)
class ControlParkingFreeSpaceServiceTest {
    @Mock
    ParkingRepository parkingRepository;

    @Mock
    ParkingPlaceRepository parkingPlaceRepository;

    @Mock
    ControlParkingFreeSpaceService controlParkingFreeSpaceService = new ControlParkingFreeSpaceService(parkingPlaceRepository, parkingRepository);

    @Mock
    ParkingFreeSpaceController parkingFreeSpaceController;

    @Test
    void shittyTest(){
        ResponseDto dto = new ResponseDto("ok","10");
        Parking parking = new Parking("moscow_parking");
        parking.setId(1L);
        List<ParkingPlace> parkingPlace = new ArrayList<>();
        Collections.addAll(
                parkingPlace,
                new ParkingPlace(parking, 1),
                new ParkingPlace(parking, 2),
                new ParkingPlace(parking, 3),
                new ParkingPlace(parking, 4),
                new ParkingPlace(parking, 5),
                new ParkingPlace(parking, 6),
                new ParkingPlace(parking, 7),
                new ParkingPlace(parking, 8),
                new ParkingPlace(parking, 9),
                new ParkingPlace(parking, 10)
        );
        Long count = 0L;
        for (ParkingPlace place : parkingPlace){
            place.setId(++count);
        }
        parking.setParkingPlace(parkingPlace);
        when(parkingPlaceRepository.countFreeSpace(anyLong())).thenReturn(parkingPlace.size());
        when(parkingRepository.findFirstByNameEquals(anyString())).thenReturn(Optional.of(parking));
        when(controlParkingFreeSpaceService.parkingControlPanel(anyString(), anyString())).thenReturn(
                new ResponseDto("ok", "10"));
        when(parkingFreeSpaceController.getFreeSpace(anyString(), anyString())).thenReturn(dto);
        assertEquals(parkingPlaceRepository.countFreeSpace(5L), 10);
        assertThat(parkingRepository.findFirstByNameEquals("okay")).isPresent();
        assertEquals(controlParkingFreeSpaceService.parkingControlPanel("moscow_parking", "status"), dto);
        assertEquals(parkingFreeSpaceController.getFreeSpace("moscow_parking", "status"), dto);
        System.out.println(parkingFreeSpaceController.getFreeSpace("moscow_parking", "status"));
    }

}