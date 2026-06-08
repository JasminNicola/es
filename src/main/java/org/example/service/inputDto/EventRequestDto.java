package org.example.service.inputDto;

import org.example.model.EventType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventRequestDto {

    private Long id;
    private String name;
    private String description;
    private String date; //Todo LocalDate
    private Integer participants;
    private String location;
    private Boolean internationalGuests;
    private EventType eventType;
    private String catering; //Todo ENUM
    private String specialNotes;
    private String status;

    public EventRequestDto() {
    }


    public EventRequestDto(Long id, String name, String description, LocalDate date, Integer participants, String location, boolean internationalGuests, EventType eventType, String catering, String specialNotes, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date != null ? date.toString() : null;
        this.participants = participants;
        this.location = location;
        this.internationalGuests = internationalGuests;
        this.eventType = eventType;
        this.catering = catering;
        this.specialNotes = specialNotes;
        this.status = status;
}

    public EventRequestDto(Long id, String name,String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
