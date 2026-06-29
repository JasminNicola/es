package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "location_availabilities")
public class LocationAvailibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_location_id", nullable = false)
    private EventLocation eventLocation;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Boolean roomAvailable;

}
