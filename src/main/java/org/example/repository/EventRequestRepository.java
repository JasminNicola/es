package org.example.repository;

import org.example.model.EventRequest;
import org.example.model.EventStatus;
import org.example.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventRequestRepository extends JpaRepository<EventRequest, Long> {

}
