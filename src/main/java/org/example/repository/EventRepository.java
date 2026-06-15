package org.example.repository;

import org.example.model.Event;
import org.example.model.EventStatus;
import org.example.service.outputDto.EventRequestDtoOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {



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
    join u.events e
    where u.id = :ownerId

        """)
        List<EventRequestDtoOutput> getEventRequestByOwnerId(@Param("ownerId") Long ownerId);


        @Query(" select e from Event e where e.feedback is not null")
        List<Event> getEventWithFeedback();

}
