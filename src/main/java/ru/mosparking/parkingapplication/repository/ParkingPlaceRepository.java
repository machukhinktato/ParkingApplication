package ru.mosparking.parkingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mosparking.parkingapplication.model.ParkingPlace;

/**
 * @author machukhinktato (Tarabrin Mikhail) 14/02/2022
 */
@Repository
public interface ParkingPlaceRepository extends JpaRepository<ParkingPlace, Long> {

    @Query("select count(p.isFree) from ParkingPlace p " +
            "where p.isFree=true and p.parking.id=:id")
    Integer countFreeSpace(Long id);

    @Query("select count(p.parking) from ParkingPlace p " +
            "where p.parking.id=:id")
    Integer parkingTotalSpaceQuantity(Long id);
    ParkingPlace findFirstByIsFreeIsTrueAndParkingId(Long parkingId);
    ParkingPlace findFirstByIsFreeIsFalseAndParkingId(Long parkingId);
}
