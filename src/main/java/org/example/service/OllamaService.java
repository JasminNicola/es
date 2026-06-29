package org.example.service;

import org.example.model.Event;
import org.example.model.EventLocation;
import org.example.service.outputDto.HotelInfoDto;
import org.example.service.outputDto.LocationInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OllamaService {

    private final RestTemplate restTemplate= new RestTemplate();

    public String checkForAvailibilHotels(LocalDate date, Integer participants, List<HotelInfoDto> hotelSimulations , List<Event> events, Boolean internationalGuests,List<LocationInfoDto> eventLocations) {
        //TODO AI prüft ob ausreichend  verfügbar sind bei internationalen Guesten, idealer weise alle participants in einem Hotel
        //TODO Liste der Hotels mit verfügbaren Zimmern zurückgeben // Prototyp anfrage H2 Database später HotelApi
        //TODO im Feedback soll geprüft werden ob Hotels negativ aufgefallen sind und diese in Zukunft nicht mehr berücksichtigt werden sollen
        Map<String, Object> message=null;
        if(internationalGuests==true) {
            message= messageHotels(date, participants, hotelSimulations, events, eventLocations);
        } else {
            message= messagewithoutInternationalGuests(date, participants, events,eventLocations);
        }

        Map<String,Object> body = Map.of(
                "model", "llama3.2",
                "messages", List.of(message),
                "stream", false
                );
        System.out.println("Ollama request body: " + body);

        Map response = restTemplate.postForObject("http://localhost:11434/api/chat", body, Map.class);

        Map response2 = (Map) response.get("message");

        return (String) response2.get("content");
    }


//TODO räume müssen übergeben und geprüft werden  Räume müssen auch bei anfragen mit hotells geprüft werdn
    private Map<String,Object> messagewithoutInternationalGuests(LocalDate date, Integer participants,  List<Event> events,List< LocationInfoDto> eventLocations) {
        Map<String, Object> messagewithoutInternationalGuests= Map.of(
                "role", "user",
                "content", "Prüfe ob am " + date + " ein raum verfügbar ist für " + participants + " Teilnehmer verfügbar ist. "


                        + " Berücksichtige auch folgende Events mit Feedback: "
                        + events.stream()
                        .filter(e -> e.getFeedback() != null)
                        .map(e -> "Event: " + e.getName() + " Feedback: " + e.getFeedback())
                        .collect(Collectors.joining("; "))
                        + "sollte es an diesem Tag nicht genügend Platz geben, schlag bitte einen alternativ Termin vor."
                        + " Wenn es keinen verfügbaren raum giebt sollte ein alternativ Termin vorgeschlagen werden,"
                        +" Bei negativem Feedback negatives Feedback sollte berücksichtigt werden"
                        + "Wenn es keine Informationen zu freine oder gebuchten Räumengibt ist der Raum nicht verfügbar"
                        +"Bitte berücksichtige die deutschen feiertage in Hessen und die zugehörigen Brückentage für eventanfragen und gib bescheid wenn es auf einen feiertag fällt und nennen den Namen des feiertags."
                        +"Bitte gib an ob es in diesem zeitraum eine Messe oder ein Großevent im Rhein-Maingebiet gibt"
        );

        return messagewithoutInternationalGuests;
    }

    private Map<String,Object> messageHotels(LocalDate date, Integer participants, List<HotelInfoDto> hotelSimulations, List<Event> events, List< LocationInfoDto> eventLocations) {
        Map<String, Object> messageHotels= Map.of(
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
                        +"Bitte berücksichtige die deutschen feiertage in Hessen und die zugehörigen Brückentage für eventanfragen und geb bescheid wenn es auf einen feiertag und nennen den Namen des feiertags."
                        +"Bitte gib an ob es in diesem zeitraum eine Messe oder ein Großevent im Rhein-Maingebiet gibt"
        );

        return messageHotels;
    }

}
