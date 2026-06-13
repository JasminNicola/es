package org.example.repository;

import org.example.model.HotelAvailibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelAvailibilityRepository extends JpaRepository<HotelAvailibility, Long> {

    @Query("SELECT h FROM HotelAvailibility h WHERE h.date = :date AND h.availableRooms >= :participants")
    List<HotelAvailibility> findByDateAndAvailableRoomsGreaterThanEqual(@Param("date") String date,@Param("participants") Integer participants);
}
