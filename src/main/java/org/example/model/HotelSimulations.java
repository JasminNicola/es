package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "hotel_simulations")
public class HotelSimulations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        private String hotelName;
        private String city;

    @OneToMany(mappedBy = "hotelSimulation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HotelAvailibility> availabilities = new ArrayList<>();
        private Double pricePerNight;


        public HotelSimulations() {
        }

        public HotelSimulations(String hotelName, String city, Integer numberOfRooms,Integer availableRooms, Double pricePerNight) {
            this.hotelName = hotelName;
            this.city = city;

            this.pricePerNight = pricePerNight;

        }
}
