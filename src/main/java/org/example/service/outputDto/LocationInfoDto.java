package org.example.service.outputDto;

import lombok.Data;

import java.util.List;

@Data
public class LocationInfoDto {
    Long id;
    String locationName;
    List<LocationAvailibilityInfoDto> availabilities;

    public LocationInfoDto(Long id, String locationName, List<LocationAvailibilityInfoDto> availabilities) {
        this.id = id;
        this.locationName = locationName;
        this.availabilities = availabilities;
    }
    public LocationInfoDto(Long id, String locationName) {
        this.id = id;
        this.locationName = locationName;
        this.availabilities = null;
    }
    public LocationInfoDto() {}

}
