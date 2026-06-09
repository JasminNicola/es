package org.example.repository;


import org.example.model.HistoricalEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HistoricalEventRepository extends JpaRepository<HistoricalEvent, Long> {

        List<HistoricalEvent> findByDescriptionContaining(String keyword);

        List<HistoricalEvent> findByLocation(String eventLocation);
}
