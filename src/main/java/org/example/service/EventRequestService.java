package org.example.service;

import org.example.model.EventRequest;
import org.example.model.EventStatus;
import org.example.model.EventType;
import org.example.model.UserEmployee;
import org.example.repository.EventRequestRepository;
import org.example.service.inputDto.EventRequestDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    public ResponseEntity<?> updateEventRequest(Long id, EventRequestDto updated) {
        Optional<EventRequest> optionalEventRequest = this.eventRequestRepository.findById(id);
        if (optionalEventRequest.isPresent()) {
            EventRequest eventRequest = optionalEventRequest.get();
            eventRequest.setName(updated.getName());
            eventRequest.setDescription(updated.getDescription());
            eventRequest.setDate(updated.getDate());
            eventRequest.setParticipants(updated.getParticipants());
            eventRequest.setLocation(updated.getLocation());
            eventRequest.setInternationalGuests(updated.isInternationalGuests());
            eventRequest.setEventType(updated.getEventType());
            eventRequest.setCatering(updated.getCatering());
            eventRequest.setSpecialNotes(updated.getSpecialNotes());
            // Status wird hier nicht aktualisiert, da es nur von Admins geändert werden sollte
            this.eventRequestRepository.save(eventRequest);
            return ResponseEntity.ok("EventItem request updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteEventRequest(Long id) {
        this.eventRequestRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    public ResponseEntity<?> getAllEventsByUser() {
        Long id =this.userService.getLoggedInUserId().getId();
        EventRequestDto eventRequestDto = this.eventRequestRepository.findByOwnerId(id);
        System.out.println("EventRequestDto: " + eventRequestDto + " wurde in db gefunden und wird jetzt zurückgegeben");
        return ResponseEntity.ok(eventRequestDto);

    }
}
