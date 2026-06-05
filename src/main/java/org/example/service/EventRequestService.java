package org.example.service;

import lombok.Data;
import org.example.model.EventRequest;
import org.example.model.EventStatus;
import org.example.model.EventType;
import org.example.repository.EventRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class EventRequestService {

    private final EventRequestRepository eventRequestRepository;

    public EventRequestService(EventRequestRepository eventRequestRepository) {
        this.eventRequestRepository = eventRequestRepository;
    }

    public void createEventRequest(String name, String description, String date, Integer participants, String location, boolean internationalGuests, EventType eventType, String catering, String specialNotes, EventStatus status) {
        EventRequest eventRequest = new EventRequest(name, description, date, participants, location, internationalGuests, eventType, catering, specialNotes, status);
        this.eventRequestRepository.save(eventRequest);
    }
}
