package org.example.model;

import jakarta.persistence.*;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Integer getParticipants() {
        return participants;
    }
    public void setParticipants(Integer participants) {
        this.participants = participants;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
