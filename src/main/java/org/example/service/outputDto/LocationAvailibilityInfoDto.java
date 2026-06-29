package org.example.service.outputDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LocationAvailibilityInfoDto {
    Long id;
    LocalDate date;
    Boolean roomAvailable;

    public LocationAvailibilityInfoDto(Long id, LocalDate date, Boolean roomAvailable) {
        this.id = id;
        this.date = date;
        this.roomAvailable = roomAvailable;
    }
    public LocationAvailibilityInfoDto() {}

}
