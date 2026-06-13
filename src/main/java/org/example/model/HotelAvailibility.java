package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "hotel_availabilities")
public class HotelAvailibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_simulation_id", nullable = false)
    private HotelSimulations hotelSimulation;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer availableRooms;

    @Column(nullable = false)
    private Integer bookedRooms;
}
