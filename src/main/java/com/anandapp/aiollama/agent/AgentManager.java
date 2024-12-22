package com.anandapp.aiollama.agent;

import com.anandapp.aiollama.services.OllamaService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.*;

@Service
public class AgentManager {
    private final OllamaService ollamaService;

    // if we define here and "shutdown()" it after first execution, we will get error "RejectedExecutionException" for upcoming request
    // so better to create new one for every request, so move below inside method
//    private final ExecutorService executorService = Executors.newFixedThreadPool(4); // Adjust pool size as needed

    public AgentManager(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    public SimulationResult runSimulation(List<String> prompts) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
        long simulationStart = System.currentTimeMillis();

        try{
            // Submit an agent for each prompt
            for (int i = 0; i < prompts.size(); i++) {
                String agentName = "Agent-" + (i + 1);
                ChatAgent agent = new ChatAgent(agentName, prompts.get(i), ollamaService, logQueue);
                executorService.submit(agent);
            }
            // Wait for all agents to complete (for demo, wait a fixed time or use executorService.awaitTermination)
            executorService.shutdown();
            boolean terminated = executorService.awaitTermination(30, TimeUnit.SECONDS);
            if (!terminated) {
                System.out.println("Simulation did not finish in the expected time.");
            }
        } finally {
            // Ensure the ExecutorService is properly shut down if it was not already done.
            if (!executorService.isShutdown()) {
                executorService.shutdownNow();  // Force shutdown if needed
            }
        }

        long simulationEnd = System.currentTimeMillis();

        // Aggregate quantitative metrics
        int totalAgents = prompts.size();
        long totalSimulationTime = simulationEnd - simulationStart;

        return new SimulationResult(List.copyOf(logQueue), totalAgents, totalSimulationTime);
    }
}
