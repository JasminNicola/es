package org.example.model;

import jakarta.persistence.*;


import java.util.List;


@Entity
@Table(name = "event_team")
public class EventTeam {//Responsible for organizing the event, can be assigned to multiple EventRequests
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToMany(mappedBy = "responsibleTeam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

    private String teamname;

    public EventTeam() {
    }

    public EventTeam(String teamname) {
        this.teamname = teamname;
    }



}
