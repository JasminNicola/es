package org.example.service.outputDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelAvailibilityInfoDto {
    private Long id;
    private LocalDate date;
    private Integer availableRooms;
    private Integer bookedRooms;//h.id,h.date,h.availableRooms,h.bookedRooms,h.hotelSimulation.id
    private Long hotelId;

     public HotelAvailibilityInfoDto(Long id, LocalDate date, Integer availableRooms, Integer bookedRooms, Long hotelId) {
            this.id = id;
         this.date = date;
         this.availableRooms = availableRooms;
         this.bookedRooms = bookedRooms;
         this.hotelId = hotelId;
        }
    public HotelAvailibilityInfoDto(Long id, LocalDate date, Integer availableRooms, Integer bookedRooms) {
        this.id = id;
        this.date = date;
        this.availableRooms = availableRooms;
        this.bookedRooms = bookedRooms;
    }


    public HotelAvailibilityInfoDto() {
    }


}
