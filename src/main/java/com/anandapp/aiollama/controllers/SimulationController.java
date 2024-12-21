package com.anandapp.aiollama.controllers;

import com.anandapp.aiollama.agent.AgentManager;
import com.anandapp.aiollama.agent.SimulationResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SimulationController {
    private final AgentManager agentManager;

    public SimulationController(AgentManager agentManager) {
        this.agentManager = agentManager;
    }

    @GetMapping("/simulate")
    public SimulationResult simulateAgents() {
        List<String> prompts = List.of(
                "what is capital of india",
                "what is the capital of Nepal",
                "Explain Java threading basics.",
                "What is agentic in software?"
        );
        try {
            return agentManager.runSimulation(prompts);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new SimulationResult(List.of("Simulation interrupted: " + e.getMessage()), 0, 0);
        }
    }
}
