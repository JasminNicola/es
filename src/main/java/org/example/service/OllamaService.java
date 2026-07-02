package org.example.service;

import org.example.model.Event;
import org.example.model.EventLocation;
import org.example.service.outputDto.HotelInfoDto;
import org.example.service.outputDto.LocationInfoDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        String prompt = """
        Task

        Evaluate the following event request.

        Event Date:
        %s

        Number of Participants:
        %d

        Available Event Locations:
        %s

        Previous Event Feedback:
        %s

        Rules

        1. Check whether an event location is available on the requested date.
        2. The selected location must have enough capacity for all participants.
        3. If no availability information exists for a location on the requested date, consider the location unavailable.
        4. Consider previous negative feedback when evaluating event locations.
        5. Check whether the requested date is a public holiday in Hesse, Germany. If so, mention the holiday by name.
        6. Consider bridge days ("Brückentage") associated with public holidays.
        7. Check whether there is a trade fair or major event in the Rhine-Main region that could affect the event.
        8. If the requested date is not suitable, suggest the closest reasonable alternative date.

        Response Format

        Possible or Not Possible

        If not possible, provide the Reason and an Alternative Date.:

        , Reason: (name the reason(s) why the requested date is not possible)
        Event location is booked or Public holiday/Bridge day+ name or Major event or Previous negative feedback

        , Alternative Date:
        <Date DD.MM.YY>

        Summary:
        - If possible: "The event request can be accepted. A team member will contact you shortly."
        - Otherwise: Explain briefly why the requested date is not possible and provide the alternative date.

        Respond only in this format without any introduction or additional explanation.
        """
                .formatted(
                        date,
                        participants,
                        getStringEventLocations(eventLocations),
                        getFeedback(events)
                );


        Map<String, Object> messagewithoutInternationalGuests= Map.of(
                "role", "user",
                "content",prompt);
//                "Prüfe ob am " + date + " eine Eventlocation verfügbar ist für " + participants + " Teilnehmer verfügbar ist. "
//                           +"es gibt folgende Eventlocations: "
//                        + getStringEventLocations(eventLocations)
//                        + " Berücksichtige auch folgende Events mit Feedback: "
//                        + getFeedback(events)
//                        + "sollte es an diesem Tag nicht genügend Platz geben, schlag bitte einen alternativ Termin vor."
//                        + " Wenn es keinen verfügbaren raum giebt sollte ein alternativ Termin vorgeschlagen werden,"
//                        +" Bei negativem Feedback negatives Feedback sollte berücksichtigt werden"
//                        + "Wenn es keine Informationen zu freine oder gebuchten Räumengibt ist der Raum nicht verfügbar"
//                        +"Bitte berücksichtige die deutschen feiertage in Hessen und die zugehörigen Brückentage für eventanfragen und gib bescheid wenn es auf einen feiertag fällt und nennen den Namen des feiertags."
//                        +"Bitte gib an ob es in diesem zeitraum eine Messe oder ein Großevent im Rhein-Maingebiet gibt"
//                        +"Antworte entweder das die Eventanfrage erfolgreich gespeichert wurde und sich jemand meldet oder antworte mit einem alternativen Termin und begründe warum der Termin nicht möglich ist. Die antwort soll so Stichpunktartig sein bitte antworte nur ob es geht oder nicht geht. wenn nicht sag nur das es aufgrund von zb Raumverfügbarkein/Hotellzimmern/Feiertagen oder anderen Events nicht funktionieret"
//
//        );

        return messagewithoutInternationalGuests;
    }

    private String getStringEventLocations(List<LocationInfoDto> eventLocations) {
        return eventLocations.stream()
                .map(l -> "Location: " + l.getLocationName() + " Verfügbarkeit: " +
                        (l.getAvailabilities() != null ? l.getAvailabilities().stream()
                                .map(a -> String.format("%d frei, gebucht %d ",
                                        a.getDate(), a.getRoomAvailable()))
                                //  .collect(Collectors.joining(", "))

                                .collect(Collectors.joining("; ")) : "Keine Verfügbarkeitsinformationen")).toString();
    }

    private Map<String,Object> messageHotels(LocalDate date, Integer participants, List<HotelInfoDto> hotelSimulations, List<Event> events, List< LocationInfoDto> eventLocations) {
        String prompt = """
Task

Evaluate the following event request.

Event Date:
%s

Number of Participants:
%d

Available Event Locations:
%s

Available Hotels:
%s

Previous Event Feedback:
%s

Rules

1. Check whether an event location is available on the requested date.
2. The selected location must have enough capacity for all participants.
3. If no availability information exists for a location on the requested date, consider the location unavailable.
4. Check whether enough hotel rooms are available.
5. If possible, accommodate all participants in one hotel.
6. Otherwise, distribute participants across multiple hotels.
7. If no hotel availability exists for the requested date, consider the hotel unavailable.
8. Consider previous negative feedback when recommending locations or hotels. Only recommend them if no better alternative exists.
9. Check whether the requested date is a public holiday in Hesse, Germany, and mention the holiday by name if applicable.
10. Consider bridge days associated with public holidays.
11. Check whether there is a trade fair or major event in the Rhine-Main area that could affect availability.
12. If the requested date is not suitable, suggest the closest reasonable alternative date.

Response Format

Status:
-Possible or Not Possible

If not possible, provide the Reason and an Alternative Date.

Important:
- If the event is NOT possible, provide ONLY ONE reason.
- Do not list multiple reasons.
- Choose the most important blocking reason only.
- Do not explain further.

Reason: <single reason only>
- Event location is booked OR Hotel is not available OR Public holiday/Bridge day + name OR Major event OR Previous negative feedback

Alternative Date:
<Date DD.MM.YY>

Summary:
- Confirm whether the event request can be accepted or not.

Respond only in this format without any introduction or additional explanation.
"""
                .formatted(
                        date,
                        participants,
                        getStringEventLocations(eventLocations),
                        getHotelInformations(hotelSimulations),
                        getFeedback(events)
                );
//                String prompt = """
//        Task
//
//        Evaluate the following event request.
//
//        Event Date:
//        %s
//
//        Number of Participants:
//        %d
//
//        Available Event Locations:
//        %s
//
//        Available Hotels:
//        %s
//
//        Previous Event Feedback:
//        %s
//
//        Rules
//
//        1. Check whether an event location is available on the requested date.
//        2. The selected location must have enough capacity for all participants.
//        3. If no availability information exists for a location on the requested date, consider the location unavailable.
//        4. Check whether enough hotel rooms are available.
//        5. If possible, accommodate all participants in one hotel.
//        6. Otherwise, distribute participants across multiple hotels.
//        7. If no hotel availability exists for the requested date, consider the hotel unavailable.
//        8. Consider previous negative feedback when recommending locations or hotels. Only recommend them if no better alternative exists.
//        9. Check whether the requested date is a public holiday in Hesse, Germany, and mention the holiday by name if applicable.
//        10. Consider bridge days associated with public holidays.
//        11. Check whether there is a trade fair or major event in the Rhine-Main area that could affect availability.
//        12. If the requested date is not suitable, suggest the closest reasonable alternative date.
//
//        Response Format
//
//        Status:
//        -Possible or Not Possible
//
//        If not possible, provide the Reason and an Alternative Date.:
//
//        Reason: (name the reason(s) why the requested date is not possible)
//        -Event location is booked or Hotel is not availability or Public holiday/Bridge day+ name or Major event or Previous negative feedback
//
////        Recommended Event Location:
////        -<Location name>
////
////        Recommended Hotel(s):
////        <Hotel name(s)>
//
//        , Alternative Date:
//        <Date DD.MM.YY>
//
//        Summary:
//        - Confirm whether the event request can be be accepted or explain why it cannot.
//
//        Respond only in this format without any introduction or additional explanation.
//        """
//                .formatted(
//                        date,
//                        participants,
//                        getStringEventLocations(eventLocations),
//                        getHotelInformations(hotelSimulations),
//                        getFeedback(events)
//                );

        Map<String, Object> messageHotels= Map.of(
                "role", "user",
               "content", prompt);
//                "Prüfe ob am " + date + " ausreichend Hotelzimmer für " + participants + " Teilnehmer verfügbar sind. "
//                        + "Bitte berücksichtige folgende Hotels: "
//                        + getHotelInformations(hotelSimulations)
//                        +"prüfe ob am " + date + " eine Eventlocation verfügbar ist für " + participants + " Teilnehmer verfügbar ist. "
//                        +"es gibt folgende Eventlocations: "
//                        + getStringEventLocations(eventLocations)
//                        + " Berücksichtige auch folgende Events mit Feedback: "
//                        + getFeedback(events)
//                        + " Wenn möglich sollen alle Personen in einem Hotel, sonst auf verschiedene Hotells aufteilen, "
//                        + "sollte es an diesem Tag nicht genügend Platz geben, schlag bitte einen alternativ Termin vor."
//                        + " Die Antwort soll eine Hotelempfehlung enthalten,"
//                        +" Bei negativem Feedback zu einem Hotel soll dies nur berücksichtigt werden, wenn kein anderes verfügbar ist."
//                        + "Wenn es keine Informationen zu freine oder gebuchten zimmern zu einem bestimmten Datum gibt heist es das die zimmer nicht verfügbnar sind"
//                        +"Bitte berücksichtige die deutschen feiertage in Hessen und die zugehörigen Brückentage für eventanfragen und geb bescheid wenn es auf einen feiertag und nennen den Namen des feiertags."
//                        +"Bitte gib an ob es in diesem zeitraum eine Messe oder ein Großevent im Rhein-Maingebiet gibt"
//                           +"Antworte entweder das die Eventanfrage erfolgreich gespeichert wurde und sich jemand meldet oder antworte mit einem alternativen Termin und begründe warum der Termin nicht möglich ist. Die antwort soll so Stichpunktartig sein"
////
//       );

        return messageHotels;
    }

    private String getFeedback(List<Event> events) {
        return events.stream()
                .filter(e -> e.getFeedback() != null)
                .map(e -> "Event: " + e.getName() + " Feedback: " + e.getFeedback())
                .collect(Collectors.joining("; "));
    }

    private String getHotelInformations(List<HotelInfoDto> hotelSimulations) {
        return hotelSimulations.stream()
                .map(h -> String.format("Hotel: %s, Verfügbarkeit: %s, Preis pro Nacht: %.2f",
                        h.getHotelName(),
                        h.getAvailabilities().stream()
                                .map(a -> String.format("%s: %d frei, %d gebucht",
                                        a.getDate(), a.getAvailableRooms(), a.getBookedRooms()))
                                .collect(Collectors.joining(", ")),
                        h.getPricePerNight()))
                .collect(Collectors.joining("\n"));
    }

}
