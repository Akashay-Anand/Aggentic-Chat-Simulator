package com.anandapp.aiollama;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private  final  ChatModel chatModel;
    public ChatController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    // initial api to test llm working
    @GetMapping("/api/v1/old-qna")
    public  String prompt(@RequestParam String m) {
        System.out.println("query: " + m);
        return  chatModel.call(m);
    }
}
