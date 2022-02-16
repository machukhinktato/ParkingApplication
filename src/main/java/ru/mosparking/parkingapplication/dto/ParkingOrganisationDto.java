package ru.mosparking.parkingapplication.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Locale;

/**
 * @author machukhinktato (Tarabrin Mikhail) 14/02/2022
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ParkingOrganisationDto {
    String name;

    public ParkingOrganisationDto(String name) {
        this.name = name.toLowerCase(Locale.ROOT);
    }
}
