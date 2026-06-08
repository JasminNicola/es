package org.example.service.outputDto;

import lombok.Data;
import org.example.model.EventStatus;
import org.example.model.EventType;

import java.time.LocalDate;

@Data
public class EventRequestDtoOutput {

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
    private EventStatus status;

    public EventRequestDtoOutput() {
    }

public EventRequestDtoOutput(Long id,
                             String name,
                             String description,
                             Integer participants,
                             String date,
                             String location,
                             Boolean internationalGuests,
                             EventType eventType,
                             String catering,
                             String specialNotes,
                             EventStatus status) {

        System.out.println("ERDtoOut erster Constr" + date);
                            this.id = id;
                            this.name = name;
                            this.description = description;
                            this.participants = participants;
                            this.location = location;
                            this.internationalGuests = internationalGuests;
                            this.eventType = eventType;
                            this.catering = catering;
                            this.specialNotes = specialNotes;
                            this.status = status;
}


    public EventRequestDtoOutput(Long id, String name, String description, Integer participants, LocalDate date, String location, boolean internationalGuests, EventType eventType, String catering, String specialNotes, EventStatus status) {
        System.out.println("ERDtoOut --- Creating EventRequestDtoOutput with date: " + date);
        System.out.println("Date as string: " + date );
        System.out.println("Date as LocalDate: " + date);
        this.id = id;
        this.name = name;
        this.description = description;
        this.participants = participants;
        this.date = date.toString(); //TODO
        this.location = location;
        this.internationalGuests = internationalGuests;
        this.eventType = eventType;
        this.catering = catering;
        this.specialNotes = specialNotes;
        this.status = status;
    }
//
//    public EventRequestDtoOutput(Long id, String name, String description, String date, Integer participants, String location, Boolean internationalGuests, EventType eventType, String catering, String specialNotes, EventStatus status) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.date = date;
//        this.participants = participants;
//        this.location = location;
//        this.internationalGuests = internationalGuests;
//        this.eventType = eventType;
//        this.catering = catering;
//        this.specialNotes = specialNotes;
//        this.status = status;
//}
}
