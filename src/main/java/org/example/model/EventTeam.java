package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.repository.HistoricalEventRepository;

import java.util.List;


@Entity
@Table(name = "event_team")
public class EventTeam {//Responsible for organizing the event, can be assigned to multiple EventRequests
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToMany(mappedBy = "responsibleTeam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricalEvent> historicalEvents;

    private String teamname;

    public EventTeam() {
    }

    public EventTeam(String teamname) {
        this.teamname = teamname;
    }



}
