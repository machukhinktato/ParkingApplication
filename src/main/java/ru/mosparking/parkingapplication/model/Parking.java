package ru.mosparking.parkingapplication.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

/**
 * @author machukhinktato (Tarabrin Mikhail) 14/02/2022
 */
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    @OneToMany(mappedBy = "parking",cascade = CascadeType.ALL)
    List<ParkingPlace> parkingPlace;

    public Parking() {
    }

    public Parking(String name) {
        this.name = name;
    }
}
