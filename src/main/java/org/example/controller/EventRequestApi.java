package org.example.controller;

import org.example.model.EventRequest;
import org.example.model.EventStatus;
import org.example.service.EventRequestService;
import org.example.service.inputDto.EventRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/event-requests")
public class EventRequestApi {

    private final EventRequestService eventRequestService;

    public EventRequestApi(EventRequestService eventRequestService) {
        this.eventRequestService = eventRequestService;
    }

    @PostMapping("/events")
    public ResponseEntity<?> createEvent(@RequestBody EventRequestDto request) {

        System.out.println("Neue Eventanfrage: " + request.getName());
        System.out.println("Datum:             " + request.getDate());
        System.out.println("Ort:               " + request.getLocation());
        System.out.println("Teilnehmer:        " + request.getParticipants());
        System.out.println("Typ:               " + request.getEventType());
        System.out.println("Intern. Gäste:     " + request.isInternationalGuests());
        System.out.println("Catering:          " + request.getCatering());

        // TODO: in Datenbank speichern via Service/Repository
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
                EventStatus.PENDING // TODO: Status auf "PENDING" setzen
        );
        return ResponseEntity.ok(
                Map.of("message", "Eventanfrage für '" + request.getName() + "' erfolgreich übermittelt!")
        );
    }

}
