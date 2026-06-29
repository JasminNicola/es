package org.example.controller;

import org.example.model.EventStatus;
import org.example.service.EventRequestService;
import org.example.service.inputDto.EventRequestDto;
import org.example.service.outputDto.EventRequestDtoOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EventRequestApi {

    private final EventRequestService eventRequestService;

    public EventRequestApi(EventRequestService eventRequestService) {
        this.eventRequestService = eventRequestService;
    }

    @PostMapping("/events/create-request")
    public ResponseEntity<?> createEvent(@RequestBody EventRequestDto request) {

        String aiResponse = this.eventRequestService.createEventRequest(
                request.getName(),
                request.getDescription(),
                request.getDate(),
                request.getParticipants(),
                request.getLocation(),
                request.getInternationalGuests(),
                request.getEventType(),
                request.getCatering(),
                request.getSpecialNotes(),
                EventStatus.PENDING
        );
        return ResponseEntity.ok(
                Map.of("message", "Eventanfrage für '" + request.getName() + "' erfolgreich übermittelt!" +
                                  " KI-Analyse: " + aiResponse)
        );
    }

    // PUT  /api/events/{id}   → EventItem aktualisieren
    @PutMapping("/events/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody EventRequestDto updated) {

        return this.eventRequestService.updateEventRequest(id,updated);
    }

    // DELETE /api/events/{id} → EventItem löschen
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return this.eventRequestService.deleteEventRequest(id);

    }

    @GetMapping("/events")
    public ResponseEntity<?> getAllPendingEvents() {

       List<EventRequestDtoOutput> response= (List<EventRequestDtoOutput>) this.eventRequestService.getAllEventsByUser().getBody();
        System.out.println("Response in Controller: " + response );
       return  ResponseEntity.ok(
                Map.of("message", "Alle Eventanfragen erfolgreich abgerufen!",
                        "response", response)      );
    }




}
