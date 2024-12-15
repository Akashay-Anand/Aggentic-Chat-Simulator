package com.anandapp.aiollama.agent;

import com.anandapp.aiollama.model.Answer;
import com.anandapp.aiollama.model.UserQuery;
import com.anandapp.aiollama.services.OllamaService;
import java.util.concurrent.BlockingQueue;

public class ChatAgent implements Runnable {
    private final String agentName;
    private final String prompt;
    private final OllamaService ollamaService;
    private final BlockingQueue<String> logQueue;

    public ChatAgent(String agentName, String prompt, OllamaService ollamaService, BlockingQueue<String> logQueue) {
        this.agentName = agentName;
        this.prompt = prompt;
        this.ollamaService = ollamaService;
        this.logQueue = logQueue;
    }

    @Override
    public void run() {
        try {
            long startTime = System.currentTimeMillis();
            UserQuery query = new UserQuery(prompt);
            Answer answer = ollamaService.getAnswer(query);
            long endTime = System.currentTimeMillis();
            String logEntry = String.format("%s: Prompt: '%s' | Response: '%s' | Time: %dms",
                    agentName, prompt, answer.answer(), (endTime - startTime));
            logQueue.put(logEntry);
            System.out.println("✔️✔️" + prompt + ": "+ logEntry);
        } catch (Exception e) {
            try {
                logQueue.put(agentName + " encountered an error: " + e.getMessage());
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
