package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ai_analyse")
public class AiAnalyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String date;
    private Integer participants;
    private String location;

    public AiAnalyse() {
    }

    public AiAnalyse(String name, String description, String date, Integer participants, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.participants = participants;
        this.location = location;
    }
}
