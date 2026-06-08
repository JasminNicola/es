package org.example.repository;

import org.example.model.EventRequest;
import org.example.model.EventStatus;
import org.example.service.outputDto.EventRequestDtoOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRequestRepository extends JpaRepository<EventRequest, Long> {

        @Query("SELECT e FROM EventRequest e WHERE e.status = :status")
        List<EventRequest> findByStatus(EventStatus status);


        @Query("""
        select new org.example.service.outputDto.EventRequestDtoOutput (e.id,
                                                                    e.name,
                                                                    e.description,
                                                                    e.participants,
                                                                    e.date,
                                                                    e.location,
                                                                    e.internationalGuests,
                                                                    e.eventType,
                                                                    e.catering,
                                                                    e.specialNotes,
                                                                    e.status
                                                            ) from UserEmployee u
    join u.eventRequests e
    where u.id = :ownerId

        """)
        List<EventRequestDtoOutput> getEventRequestByOwnerId(@Param("ownerId") Long ownerId);

}
