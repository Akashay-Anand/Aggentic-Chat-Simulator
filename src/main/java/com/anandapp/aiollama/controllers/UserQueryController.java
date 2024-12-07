package com.anandapp.aiollama.controllers;

import com.anandapp.aiollama.model.Answer;
import com.anandapp.aiollama.model.UserQuery;
import com.anandapp.aiollama.services.OllamaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserQueryController {

    private final OllamaService ollamaService;

    public UserQueryController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }


    @PostMapping("/ask")
    public Answer askQuestion(@RequestParam UserQuery query){
        System.out.println("🟧🟧 in controller user query: "+ query);
        return ollamaService.getAnswer(query);
    }
}
