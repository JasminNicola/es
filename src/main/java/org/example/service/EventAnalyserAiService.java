package org.example.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.example.model.Event;
import org.example.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventAnalyserAiService {

    private final OllamaChatModel chatLanguageModel;
    private final EventRepository eventRepository;

    public EventAnalyserAiService( OllamaChatModel chatLanguageModel, EventRepository eventRepository) {
        this.chatLanguageModel = chatLanguageModel;
        this.eventRepository = eventRepository;
    }

    public String analyseNewEventRequest(String newEventDescription) { //TODO Dto erstellen

        List<Event> pastEvents = eventRepository.findAll(); //.findByLocation(eventLocation);//TODO abgeschlossen //TODO Rückgabe  DTO erstellen, damit nicht alle Daten übergeben werden, sondern nur relevante Informationen (z.B. Beschreibung, Team, Catering, Feedback)

        // 2. Ihre bestehenden Objekte in Text für Ollama umwandeln
        String pastEventInformaton = pastEvents.stream().map(e ->
                String.format("Event: %s\nDescription: %s\nFeedback/Notes: %s\nTeam: %s\nCatering: %s\n---",
                        e.getName(),
                        e.getDescription(),
                        e.getFeedback(),
                        e.getResponsibleTeam(),
                        e.getCatering()// vieleicht catering vorschlagen wenn Events gleicher art Catering hatten

                )
        ).collect(Collectors.joining("\n"));

        // 3. Prompt anpassen und an Ollama senden
        String prompt = String.format("""
            You are an advanced event analysis system. Here is a list of past events from our database:
                %s
               \s
                Here is the description of a NEW event:
                "%s"
               \s
                Your Tasks:
                1. Compare the new event with past data. Are there any strong similarities?
                2. Which team should be assigned to this new event based on past responsibilities?
                3. What follow-up questions do we need to ask the organizer (e.g., based on past feedback or catering suggestions)?
                4. If this is an international event, check the hotel availability in the target city for the given date using the available tools. If hotels are fully booked, suggest an alternative date.
               \s
                Respond in a short, structured format in English.
            """, pastEventInformaton, newEventDescription);

        return chatLanguageModel.generate(prompt);
    }
}
