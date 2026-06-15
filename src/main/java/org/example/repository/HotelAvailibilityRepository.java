package org.example.repository;

import org.example.model.HotelAvailibility;
import org.example.model.HotelSimulations;
import org.example.service.outputDto.HotelAvailibilityInfoDto;
import org.example.service.outputDto.HotelInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface HotelAvailibilityRepository extends JpaRepository<HotelAvailibility, Long> {

//    @Query("SELECT new org.example.service.outputDto.HotelAvailibilityInfoDto(h.id,h.date,h.availableRooms,h.bookedRooms,h.hotelSimulation.id)  FROM HotelAvailibility h WHERE h.date = :date AND h.availableRooms >= :participants") //Todo oder größer als 0?
//    List<HotelAvailibilityInfoDto> findByDateAndAvailableRoomsGreaterThanEqual(@Param("date") LocalDate date, @Param("participants") Integer participants);
//
//
//    List<HotelSimulations> findHotels();

    @Query("""
    SELECT new org.example.service.outputDto.HotelInfoDto(h.id,h.hotelName,h.city, h.pricePerNight) FROM HotelSimulations h
    JOIN h.availabilities ha
    WHERE ha.availableRooms > 0
""")
    List<HotelInfoDto> findAllHotelSimulations();

    @Query("SELECT new org.example.service.outputDto.HotelAvailibilityInfoDto(h.id,h.date,h.availableRooms,h.bookedRooms)  FROM HotelAvailibility h WHERE h.hotelSimulation.id= :id") //Todo oder größer als 0?
    List<HotelAvailibilityInfoDto> getAvailibilityByHotelId(@Param("id") Long id);



}
