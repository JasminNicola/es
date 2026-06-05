package org.example.model;

import jakarta.persistence.*;


@Entity
@Table(name = "event_team")
public class EventTeam {
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
