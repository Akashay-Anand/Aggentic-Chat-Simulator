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
            Answer initialAnswer = ollamaService.getAnswer(query);
            String finalResponse = initialAnswer.answer();

            // Evaluate response (if too long prompt again it... etc) || Dynamic Follow-Up
//          // each agent can check if its response meets certain quality criteria
            if (finalResponse.length() > 50) {
                UserQuery followUpPrompt = new UserQuery("Please provide small answer. it should be under 50 words length for this question: " + prompt);
                Answer followUpAnswer = ollamaService.getAnswer(followUpPrompt);
                finalResponse = "\n[Follow-Up]: " + followUpAnswer.answer();
            }

            long endTime = System.currentTimeMillis();
            String logEntry = String.format("%s: Prompt: '%s' | Response: '%s' | Time: %dms",
                    agentName, prompt, finalResponse , (endTime - startTime));
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
