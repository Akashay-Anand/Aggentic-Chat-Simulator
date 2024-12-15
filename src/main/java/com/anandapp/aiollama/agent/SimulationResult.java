package com.anandapp.aiollama.agent;

import java.util.List;

public class SimulationResult {
    private final List<String> logs;
    private final int totalAgents;
    private final long totalSimulationTime; // in milliseconds

    public SimulationResult(List<String> logs, int totalAgents, long totalSimulationTime) {
        this.logs = logs;
        this.totalAgents = totalAgents;
        this.totalSimulationTime = totalSimulationTime;
    }

    public List<String> getLogs() {
        return logs;
    }

    public int getTotalAgents() {
        return totalAgents;
    }

    public long getTotalSimulationTime() {
        return totalSimulationTime;
    }

    @Override
    public String toString() {
        return "SimulationResult{" +
                "logs=" + logs +
                ", totalAgents=" + totalAgents +
                ", totalSimulationTime=" + totalSimulationTime + "ms" +
                '}';
    }
}
