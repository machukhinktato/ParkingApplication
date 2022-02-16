package ru.mosparking.parkingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mosparking.parkingapplication.model.Parking;

import java.util.Optional;

/**
 * @author machukhinktato (Tarabrin Mikhail) 14/02/2022
 */
@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
    Optional<Parking> findFirstByNameEquals(String name);
}
