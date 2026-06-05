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
    @JoinColumn(name = "responsible_team_id")
    private EventTeam responsibleTeam; // Team, das für die Durchführung des Events verantwortlich war

    private String name;
    private String description;
    private String date;
    private Integer participants;
    private String location;


    public HistoricalEvent() {
    }

    public HistoricalEvent(String name, String description, String date, Integer participants, String location) {//,EventTeam eventTeam
        this.name = name;
        this.description = description;
        this.date = date;
        this.participants = participants;
        this.location = location;
     //   this.eventTeam = eventTeam;


    }


}
