package ru.mosparking.parkingapplication.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * @author machukhinktato (Tarabrin Mikhail) 14/02/2022
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResponseDto implements Serializable {
    String status;
    String message;
}

