package org.example.service;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface ChatService {

    @SystemMessage("Du bist ein hilfreicher Assistent. Antworte immer auf Englisch. in einem Satz")
    String chat(String message);

}

