package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "historical_event")
public class HistoricalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEmployee owner; // hat Anfrage gestellt

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
    // Special guest: "approved" -> Hi
    private String feedback; // Feedback zum Event, z.B. aus Umfragen oder Berichten



    public HistoricalEvent() {
    }

    public HistoricalEvent(String name,String description,String date, Integer participants, String location,EventType eventType,String catering, String specialNotes, EventStatus eventStatus, String feedback ) {//,EventTeam eventTeam
        this.name = name;
        this.description = description;
        this.date = date;
        this.participants = participants;
        this.location = location;
        this.eventType = eventType;
        this.catering = catering;
        this.specialNotes = specialNotes;
        this.status = eventStatus;
        this.feedback = feedback;
        this.responsibleTeam = null; // Wird später gesetzt, wenn das Event abgeschlossen ist
        this.owner = null; // Wird später gesetzt, wenn die Anfrage gestellt wird



    }


}
