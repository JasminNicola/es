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
    private String name;
    private String description;
    private String date;
    private Integer participants;
    private String location;

//    @OneToMany(mappedBy = "historicalEvent")
//    private EventTeam eventTeam;
//    public HistoricalEvent() {
//    }

    public HistoricalEvent(Long id,String name, String description, String date, Integer participants, String location) {//,EventTeam eventTeam
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.participants = participants;
        this.location = location;
     //   this.eventTeam = eventTeam;


    }

    public HistoricalEvent() {

    }

}
