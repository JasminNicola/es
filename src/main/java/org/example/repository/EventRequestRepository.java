package org.example.repository;

import org.example.model.EventRequest;
import org.example.model.EventStatus;
import org.example.service.inputDto.EventRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRequestRepository extends JpaRepository<EventRequest, Long> {

        @Query("SELECT e FROM EventRequest e WHERE e.status = :status")
        List<EventRequest> findByStatus(EventStatus status);

        @Query ("SELECT e FROM EventRequest e WHERE e.owner.id = :id")
        EventRequestDto findByOwnerId(@Param("id") Long id);

//        @Query("select new org.example.service.inputDto.EventRequestDto(e.id, e.name,e.date,e.participants,e.location,e.internationalGuests, e.eventType,e.catering,e.specialNotes,e.status) from EventRequest e where e.id = :id")
//        EventRequestDto findById(@Param("id") Long id);


}
