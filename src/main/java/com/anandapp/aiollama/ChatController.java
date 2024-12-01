package com.anandapp.aiollama;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    // ok test
    private  final  ChatModel chatModel;

    public ChatController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/")
    public  String prompt(@RequestParam String m) {
        System.out.println("query: " + m);
        return  chatModel.call(m);
    }
    @GetMapping("/error")
    public  String errorres(@RequestParam String m) {

        return  "error";
    }
}
