package org.example.repository;

import org.example.model.EventLocation;
import org.example.service.outputDto.HotelAvailibilityInfoDto;
import org.example.service.outputDto.HotelInfoDto;
import org.example.service.outputDto.LocationAvailibilityInfoDto;
import org.example.service.outputDto.LocationInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventLocationRepository extends JpaRepository<EventLocation, Long> {



    @Query("""
    SELECT new org.example.service.outputDto.LocationInfoDto(l.id,l.locationName) FROM EventLocation l """)
    List<LocationInfoDto> findAllLocations();

    @Query("SELECT new org.example.service.outputDto.LocationAvailibilityInfoDto(l.id,l.date,l.roomAvailable)  FROM LocationAvailibility l WHERE l.id= :id") //Todo oder größer als 0?
    List<LocationAvailibilityInfoDto> getAvailibilityByLocationId(@Param("id") Long id);

}
