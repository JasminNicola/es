package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hotel_simulations")
public class HotelSimulations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String hotelName;
        private String city;
        private Integer numberOfRooms;
        private Integer availableRooms;
        private double pricePerNight;


        public HotelSimulations() {
        }

        public HotelSimulations(String hotelName, String city, int numberOfRooms, double pricePerNight) {
            this.hotelName = hotelName;
            this.city = city;
            this.numberOfRooms = numberOfRooms;
            this.availableRooms = numberOfRooms; 
            this.pricePerNight = pricePerNight;

        }
}
