package org.example.service;

import org.example.controller.ChatControllerApi;
import org.example.model.*;
import org.example.repository.EventRepository;
import org.example.repository.HotelAvailibilityRepository;
import org.example.service.inputDto.EventRequestDto;
import org.example.service.outputDto.EventRequestDtoOutput;
import org.example.service.outputDto.HotelAvailibilityInfoDto;
import org.example.service.outputDto.HotelInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventRequestService {

    private final EventRepository eventRepository;
    private final HotelAvailibilityRepository hotelAvailibilityRepository;
    private final UserService userService;
    private final OllamaService olamaService;


    public EventRequestService(EventRepository eventRepository, HotelAvailibilityRepository hotelAvailibilityRepository, UserService userService, OllamaService olamaService) {
        this.hotelAvailibilityRepository = hotelAvailibilityRepository;
        this.olamaService = olamaService;
        this.eventRepository = eventRepository;
        this.userService = userService;

    }

    public void createEventRequest(String name, String description, LocalDate date, Integer participants, String location, Boolean internationalGuests, EventType eventType, String catering, String specialNotes, EventStatus status) {
        Event event = new Event(name, description, date, participants, location, internationalGuests, eventType, catering, specialNotes, status);
        UserEmployee owner= this.userService.getLoggedInUserId();
        if (owner != null) {
            event.setOwner(owner);
        }
        this.eventRepository.save(event);

        if (internationalGuests) {
            createPromtForAIAnalysis(date, participants);
        }
    }

    private void createPromtForAIAnalysis(LocalDate date, Integer participants) {
        //TODO AI prüft ob ausreichend  verfügbar sind bei internationalen Guesten, idealer weise alle participants in einem Hotel

        //TODO Liste der Hotelsmit verfügbaren Zimmern zurückgeben // Prototyp anfrage H2 Database später HotelApi
        List<Event> events = this.eventRepository.getEventWithFeedback();//fürs feedback


        List<HotelInfoDto> hotelSimulations = this.hotelAvailibilityRepository.findAllHotelSimulations();
        for (HotelInfoDto h: hotelSimulations) {
            h.setAvailabilities(hotelAvailibilityRepository.getAvailibilityByHotelId(h.getId()));
        }

        String responseHotelAvailibility = olamaService.checkForAvailibilHotels(date, participants,hotelSimulations, events);
        // TODO von hier muss mit der info weiter gearbeitet werden

    }


    public ResponseEntity<?> updateEventRequest(Long id, EventRequestDto updated) {
        Optional<Event> optionalEventRequest = this.eventRepository.findById(id);
        if (optionalEventRequest.isPresent()) {
            Event event = optionalEventRequest.get();
            event.setName(updated.getName());
            event.setDescription(updated.getDescription());
            event.setDate(updated.getDate());
            event.setParticipants(updated.getParticipants());
            event.setLocation(updated.getLocation());
            event.setInternationalGuests(updated.getInternationalGuests());
            event.setEventType(updated.getEventType());
            event.setCatering(updated.getCatering());
            event.setSpecialNotes(updated.getSpecialNotes());
            // Status wird hier nicht aktualisiert, da es nur von Admins geändert werden sollte
            this.eventRepository.save(event);
            return ResponseEntity.ok("EventItem request updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteEventRequest(Long id) {
        this.eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    public ResponseEntity<?> getAllEventsByUser() {
        Long id =this.userService.getLoggedInUserId().getId();
        System.out.println("User ID: " + id);
       //EventRequestDto eventRequestDto = this.eventRequestRepository.findByOwnerId(id);
        //System.out.println("EventRequestDto: " + eventRequestDto + " wurde in db gefunden und wird jetzt zurückgegeben");
        List<EventRequestDtoOutput>eventRequestDtos= this.eventRepository.getEventRequestByOwnerId(id);

        System.out.println("EventRequestDtos: " + eventRequestDtos + " wurden in db gefunden und werden jetzt zurückgegeben");
        return ResponseEntity.ok(eventRequestDtos);

    }

}
