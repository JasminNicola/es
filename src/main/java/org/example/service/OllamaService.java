package org.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class OllamaService {

    private final RestTemplate restTemplate= new RestTemplate();

    public String checkForAvailibilHotels(LocalDate date, Integer participants) {
        //TODO AI prüft ob ausreichend  verfügbar sind bei internationalen Guesten, idealer weise alle participants in einem Hotel
        Map<String,Object> massage = Map.of(
                "role", "user", "content", "Prüfe ob am " + date + " ausreichend Hotelzimmer für " + participants + " Teilnehmer verfügbar sind. Bitte bestätige kurz dass du die Hotelanfrage prüfst."
        );

        Map<String,Object> body = Map.of(
                "model", "llama3.2",
                "messages", List.of(massage),
                "stream", false
                );

        Map response = restTemplate.postForObject("http://localhost:11434/api/chat", body, Map.class);

        Map response2 = (Map) response.get("message");


        System.out.println("Ollama response: " + response2);

        return (String) response2.get("content");
    }
}
