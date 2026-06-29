package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "event_locations")
public class EventLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String locationName;

    public EventLocation() {
    }

    public EventLocation(String locationName) {
        this.locationName = locationName;
    }

    @OneToMany(mappedBy = "eventLocation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LocationAvailibility> availabilities = new ArrayList<>();
}
