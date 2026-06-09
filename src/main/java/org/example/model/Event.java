package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEmployee owner;

    @ManyToOne
    @JoinColumn(name = "responsibleTeam_id")
    private EventTeam responsibleTeam; // Team, das für die Durchführung des Events verantwortlich war


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
    private String feedback; // Feedback zum Event, z.B. aus Umfragen oder Berichten/ //TODO erst wenn das Event abgeschlossen ist
    public Event() {
    }
    public Event(String name, String description, String date, Integer participants, String location, Boolean internationalGuests, EventType eventType, String catering, String specialNotes, EventStatus status) {
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
