package org.example.service.outputDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.example.model.HotelAvailibility;

import java.util.ArrayList;
import java.util.List;

@Data
public class HotelInfoDto {
    private Long id;
    private String hotelName;
    private String city;

    private List<HotelAvailibilityInfoDto> availabilities = new ArrayList<>();
    private Double pricePerNight;

    public HotelInfoDto() {
    }
    public HotelInfoDto(Long id, String hotelName, String city, List<HotelAvailibilityInfoDto> availabilities, Double pricePerNight) {
        this.id = id;
        this.hotelName = hotelName;
        this.city = city;
        this.availabilities = availabilities;
        this.pricePerNight = pricePerNight;
    }
    public HotelInfoDto(Long id, String hotelName, String city, Double pricePerNight) {
        this.id = id;
        this.hotelName = hotelName;
        this.city = city;
        this.availabilities = null;
        this.pricePerNight = pricePerNight;
    }


}
