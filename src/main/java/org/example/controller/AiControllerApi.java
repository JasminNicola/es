package org.example.controller;

import org.example.service.EventAnalyserAiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiControllerApi {

    private final EventAnalyserAiService aiService;

    public AiControllerApi(EventAnalyserAiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/analyze")
    public String analyseNewEventRequest(@RequestBody String newEventRequest) { //TODO Dto erstellen
        return aiService.analyseNewEventRequest(newEventRequest);
    }
}
