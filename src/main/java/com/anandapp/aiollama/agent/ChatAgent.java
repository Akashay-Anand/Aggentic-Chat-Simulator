package com.anandapp.aiollama.agent;

import com.anandapp.aiollama.model.Answer;
import com.anandapp.aiollama.model.UserQuery;
import com.anandapp.aiollama.services.OllamaService;
import java.util.concurrent.BlockingQueue;

public class ChatAgent implements Runnable {

    private final String prompt;
    private final OllamaService ollamaService;


    public ChatAgent( String prompt, OllamaService ollamaService) {
        this.prompt = prompt;
        this.ollamaService = ollamaService;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        UserQuery query = new UserQuery(prompt);
        Answer answer = ollamaService.getAnswer(query);
        long endTime = System.currentTimeMillis();
        String logEntry = String.format(" Prompt: '%s' | Response: '%s' | Time: %dms",
                 prompt, answer.answer(), (endTime - startTime));

        System.out.println("✔️✔️" + prompt + ": "+ logEntry);

    }
}
