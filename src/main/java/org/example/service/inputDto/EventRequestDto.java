package org.example.service.inputDto;

import org.example.model.EventType;
import lombok.Data;

@Data
public class EventRequestDto {

    private String name;
    private String description;
    private String date; //Todo LocalDate
    private Integer participants;
    private String location;
    private boolean internationalGuests;
    private EventType eventType;
    private String catering; //Todo ENUM
    private String specialNotes;
    private String status;


//    public EventRequestDto(String name, String description, String date, Integer participants, String location, boolean internationalGuests, EventType eventType, String catering, String specialNotes, String status) {
//
//        this.name = name;
//        this.description = description;
//        this.date = date;
//        this.participants = participants;
//        this.location = location;
//        this.internationalGuests = internationalGuests;
//        this.eventType = eventType;
//        this.catering = catering; //Todo ENUM
//        this.specialNotes = specialNotes;
//        this.status = status;
//    }
//    public EventRequestDto() {}


}
