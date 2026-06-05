package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event_request")
public class EventRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String date;
    private Integer participants;
    private String location;
    private String status; // z.B. "pending", "approved", "rejected"
    // Special guest: "approved" -> HistoricalEvent, "rejected" -> EventRequest bleibt aber mit Status "rejected"
    //
    public EventRequest() {
    }
    public EventRequest(Long id, String name, String description, String date, Integer participants, String location) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.participants = participants;
        this.location = location;
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
