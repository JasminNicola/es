package org.example.service;

import lombok.Data;
import org.example.model.EventRequest;
import org.example.model.EventStatus;
import org.example.model.EventType;
import org.example.model.UserEmployee;
import org.example.repository.EventRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class EventRequestService {

    private final EventRequestRepository eventRequestRepository;
    private final UserService userService;

    public EventRequestService(EventRequestRepository eventRequestRepository, UserService userService) {
        this.eventRequestRepository = eventRequestRepository;
        this.userService = userService;
    }

    public void createEventRequest(String name, String description, String date, Integer participants, String location, boolean internationalGuests, EventType eventType, String catering, String specialNotes, EventStatus status) {
        EventRequest eventRequest = new EventRequest(name, description, date, participants, location, internationalGuests, eventType, catering, specialNotes, status);
        UserEmployee owner= this.userService.getLoggedInUserId();
        if (owner != null) {
            eventRequest.setOwner(owner);
        }
        this.eventRequestRepository.save(eventRequest);
    }
}
