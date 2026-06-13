package org.example.controller;

import org.example.service.ChatService;
import org.example.service.inputDto.ChatRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatControllerApi {
    private final ChatService chatService;

    public ChatControllerApi(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequestDto request) {
        System.out.println("Received chat message: " + request.getMessage());
        String response = chatService.chat(request.getMessage());
        System.out.println("Chat response: " + response);
        return response;
    }
}
