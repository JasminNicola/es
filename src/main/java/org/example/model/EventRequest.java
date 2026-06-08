package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "event_request")
public class EventRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEmployee owner;

    private String name;
    private String description;
    private String date;
    private Integer participants;
    private String location;
    private Boolean   internationalGuests;
    //@Enumerated(EnumType.STRING)
    private EventType eventType;
    private String    catering;          // NONE | BEVERAGES | SNACKS | FULL_MEALS
    private String    specialNotes;
    private EventStatus status; // z.B. "pending", "approved", "rejected"
    // Special guest: "approved" -> HistoricalEvent, "rejected" -> EventRequest bleibt aber mit Status "rejected"
    //
    public EventRequest() {
    }
    public EventRequest(String name, String description, String date, Integer participants, String location, Boolean internationalGuests, EventType eventType, String catering, String specialNotes, EventStatus status) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.participants = participants;
        this.location = location;
        this.internationalGuests = internationalGuests;
        this.eventType = eventType;
        this.catering = catering; //Todo ENUM
        this.specialNotes = specialNotes;
        this.status = status;
    }

    public void createNewEventRequest(String name, String description, String date, Integer participants, String location, Boolean internationalGuests, EventType eventType, String catering, String specialNotes,EventStatus status) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.participants = participants;
        this.location = location;
        this.internationalGuests = internationalGuests;
        this.eventType = eventType;
        this.catering = catering; //Todo ENUM?
        this.specialNotes = specialNotes;
        this.status = EventStatus.PENDING; // Standardstatus bei Erstellung
    }

}
