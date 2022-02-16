package ru.mosparking.parkingapplication.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Locale;

/**
 * @author machukhinktato (Tarabrin Mikhail) 14/02/2022
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalParkingSpaceDto {
    String name;
    Integer quantity;

    public AdditionalParkingSpaceDto(String name) {
        this.name = name.toLowerCase(Locale.ROOT);
    }

    public AdditionalParkingSpaceDto(String name, Integer quantity) {
        this.name = name.toLowerCase(Locale.ROOT);
        this.quantity = quantity;
    }
}
