package ru.mosparking.parkingapplication.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author machukhinktato (Tarabrin Mikhail) 14/02/2022
 */
@Entity
@Data
public class ParkingPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer parkingNumber;
    Boolean isFree = true;
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "parking_id")
    Parking parking;

    public ParkingPlace() {
    }

    public ParkingPlace(Parking parking, Integer parkingNumber) {
        this.parking = parking;
        this.parkingNumber = parkingNumber;
    }

    @Override
    public String toString() {
        return "ParkingPlace{" +
                "parking place=" + id + ", " +
                "parking is free=" + isFree.toString() +
                '}';
    }
}
