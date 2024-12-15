package com.anandapp.aiollama.agent;

import com.anandapp.aiollama.services.OllamaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.*;

@Service
public class AgentManager {
    private final OllamaService ollamaService;
    // threads for parallel processing
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public AgentManager(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    public SimulationResult runSimulation(List<String> prompts) throws InterruptedException {
        BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
        long simulationStart = System.currentTimeMillis();

        // start an agent for each prompt
        for (int i = 0; i < prompts.size(); i++) {
            String agentName = "Agent-" + (i + 1);
            ChatAgent agent = new ChatAgent(agentName, prompts.get(i), ollamaService, logQueue);
            executorService.submit(agent);
        }
        // Wait for all agents to complete (wait a fixed time or use executorService.awaitTermination)
        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);

        long simulationEnd = System.currentTimeMillis();

        // Aggregate quantitative metrics
        int totalAgents = prompts.size();
        long totalSimulationTime = simulationEnd - simulationStart;

        return new SimulationResult(List.copyOf(logQueue), totalAgents, totalSimulationTime);
    }
}
