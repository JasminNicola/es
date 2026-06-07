package org.example.controller;

import org.example.model.EventRequest;
import org.example.model.EventStatus;
import org.example.service.EventRequestService;
import org.example.service.inputDto.EventRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventRequestApi {

    private final EventRequestService eventRequestService;

    public EventRequestApi(EventRequestService eventRequestService) {
        this.eventRequestService = eventRequestService;
    }

    @PostMapping("/create-request")
    public ResponseEntity<?> createEvent(@RequestBody EventRequestDto request) {

        this.eventRequestService.createEventRequest(
                request.getName(),
                request.getDescription(),
                request.getDate(),
                request.getParticipants(),
                request.getLocation(),
                request.isInternationalGuests(),
                request.getEventType(),
                request.getCatering(),
                request.getSpecialNotes(),
                EventStatus.PENDING
        );
        return ResponseEntity.ok(
                Map.of("message", "Eventanfrage für '" + request.getName() + "' erfolgreich übermittelt!")
        );
    }

    // PUT  /api/events/{id}   → EventItem aktualisieren
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody EventRequestDto updated) {

        return this.eventRequestService.updateEventRequest(id,updated);
    }

    // DELETE /api/events/{id} → EventItem löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return this.eventRequestService.deleteEventRequest(id);

    }

    @GetMapping()
    public ResponseEntity<?> getAllPendingEvents() {
        return ResponseEntity.ok(this.eventRequestService.getAllEventsByUser());
    }




}
