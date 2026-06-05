package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hotel_simulations")
public class HotelSimulations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        private String hotelName;
        private String city;
        private Integer numberOfRooms;
        private Integer availableRooms;
        private Double pricePerNight;


        public HotelSimulations() {
        }

        public HotelSimulations(String hotelName, String city, Integer numberOfRooms,Integer availableRooms, Double pricePerNight) {
            this.id =id;
            this.hotelName = hotelName;
            this.city = city;
            this.numberOfRooms = numberOfRooms;
            this.availableRooms = availableRooms;
            this.pricePerNight = pricePerNight;

        }
}
