package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "event_team")
public class EventTeam {//Responsible for organizing the event, can be assigned to multiple EventRequests
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamname;

    public EventTeam() {
    }
    public EventTeam(String teamname) {

        this.teamname = teamname;
    }



}
