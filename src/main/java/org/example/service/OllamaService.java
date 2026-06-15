package org.example.service;

import org.example.model.Event;
import org.example.model.HotelSimulations;
import org.example.service.outputDto.HotelInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OllamaService {

    private final RestTemplate restTemplate= new RestTemplate();

    public String checkForAvailibilHotels(LocalDate date, Integer participants, List<HotelInfoDto> hotelSimulations , List<Event> events) {
        //TODO AI prüft ob ausreichend  verfügbar sind bei internationalen Guesten, idealer weise alle participants in einem Hotel
        //TODO Liste der Hotels mit verfügbaren Zimmern zurückgeben // Prototyp anfrage H2 Database später HotelApi
        //TODO im Feedback soll geprüft werden ob Hotels negativ aufgefallen sind und diese in Zukunft nicht mehr berücksichtigt werden sollen
        System.out.println("OllamaService checkForAvailibilHotels called with date: " + date + ", participants: " + participants);

//        Map<String,Object> message = Map.of(
//                "role", "user", "content", "Prüfe ob am " + date + " ausreichend Hotelzimmer für " + participants + " Teilnehmer verfügbar sind." +
//                        "Bitte berücksichtige folgende Hotels:" + hotelSimulations.stream()
//                        .map(h -> String.format("Hotel: %s, Verfügbarkeit: %s , Preis pro Nacht: %.2f"
//                                , h.getHotelName(), h.getAvailabilities().stream().map(a -> String.format(String.valueOf(a.getDate()),a.getAvailableRooms(),a.getBookedRooms())), h.getPricePerNight()
//                        ))
//                        .collect(Collectors.joining("\n"))+
//                        "Berücksichtige auch folgende Events mit Feedback:" + events.stream().filter(e -> e.getFeedback() != null).map(e -> "Event: " + e.getName() + " Feedback: " + e.getFeedback()).toList() +
//                        " Bitte bestätige kurz dass du die Hotelanfrage prüfst. Wenn möglich sollen alle in einem Hotel sein, sollte es an diesem Tag nicht genügend platz geben schlag bitte einen Termin vor."
//                +                 " Bitte gib die Antwort in folgendem Format zurück: " +
//                        "{ \"message\": { \"content\": \"Antwort hier\" } }"+
//                        " Die antwort soll eine Hotelempfehlung enthalten, wenn nötig auf mehrere hotells aufteilen oder einen alternativ termin mit hotel vorschlagen , bei negativem Feedback zu einem Hotel soll dies nur berücksichtigt werden wenn kein anderes verfügbar ist.."
//        );

        Map<String, Object> message = Map.of(
                "role", "user",
                "content", "Prüfe ob am " + date + " ausreichend Hotelzimmer für " + participants + " Teilnehmer verfügbar sind. "
                        + "Bitte berücksichtige folgende Hotels: "
                        + hotelSimulations.stream()
                        .map(h -> String.format("Hotel: %s, Verfügbarkeit: %s, Preis pro Nacht: %.2f",
                                h.getHotelName(),
                                h.getAvailabilities().stream()
                                        .map(a -> String.format("%s: %d frei, %d gebucht",
                                                a.getDate(), a.getAvailableRooms(), a.getBookedRooms()))
                                        .collect(Collectors.joining(", ")),
                                h.getPricePerNight()))
                        .collect(Collectors.joining("\n"))
                        + " Berücksichtige auch folgende Events mit Feedback: "
                        + events.stream()
                        .filter(e -> e.getFeedback() != null)
                        .map(e -> "Event: " + e.getName() + " Feedback: " + e.getFeedback())
                        .collect(Collectors.joining("; "))
                        + " Wenn möglich sollen alle Personen in einem Hotel, sonst auf verschiedene Hotells aufteilen, "
                        + "sollte es an diesem Tag nicht genügend Platz geben, schlag bitte einen alternativ Termin vor."
                        + " Die Antwort soll eine Hotelempfehlung enthalten,"
                        +" Bei negativem Feedback zu einem Hotel soll dies nur berücksichtigt werden, wenn kein anderes verfügbar ist."
                + "Wenn es keine Informationen zu freine oder gebuchten zimmern zu einem bestimmten Datum gibt heist es das die zimmer nicht verfügbnar sind"
        );

        System.out.println("Ollama request: " + message);

        Map<String,Object> body = Map.of(
                "model", "llama3.2",
                "messages", List.of(message),
                "stream", false
                );
        System.out.println("Ollama request body: " + body);

        Map response = restTemplate.postForObject("http://localhost:11434/api/chat", body, Map.class);

        Map response2 = (Map) response.get("message");


        System.out.println("Ollama response: " + response2);

        return (String) response2.get("content");
    }

}
