package org.example.repository;

import org.example.model.EventLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLocationRepository extends JpaRepository<EventLocation, Long> {
}
