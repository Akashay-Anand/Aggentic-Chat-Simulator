# Project: Agentic Chat Simulator 
- **Purpose**: Demonstrate Java and LLM skill's
- **Tech**: **Spring Boot**, **gen-ai (llm ollama)**, 
- 

## Overview
> The Agentic Chat Simulator is a Spring Boot application that demonstrates:
- **Autonomous Agent Behavior**: Multiple chat agents (threads) independently send queries to an `LLM` (via our Ollama integration) and process responses `concurrently`.
- **Multithreading & Concurrency**: The system leverages `Java's ExecutorService` to run agents in parallel, showcasing `effective thread management`.
- **Quantitative Metrics**: The application `collects performance data` (such as response times and total simulation duration) which can be used as `tangible metrics` to showcase `engineering impact`.

## Architecture & Core Concepts

### Agentic Design
> Independent Agents:
- Each chat agent is an autonomous unit that processes its prompt without reliance on other agents. This is inspired by `agentic AI principles`—systems where agents make independent decisions and operate with a level of self-sufficiency.

> Decoupled Processing:
- Agents are run in separate threads and communicate results `asynchronously` via a thread-safe log (e.g., a BlockingQueue). `This decoupled design is critical in building scalable, autonomous systems`.

> Quantitative Feedback:
- The system tracks and aggregates performance metrics such as:
- - Individual agent response times.
- - Total simulation duration.
- - Total number of agents engaged.

### Multithreading & Concurrency
> Thread Pool Execution:
- Using ExecutorService to manage a fixed-size thread pool allows the system to `efficiently handle multiple simultaneous queries`.
> Thread Safety:
- The use of a `BlockingQueue` for logging ensures that data from `concurrent threads` is `aggregated safely`.

# Milestone ✅
> Setup local LLM model ✅
>- [x] download Ollama ✔️
>- [x] install llama3.2 model ✔️
>- [x] test model ✔️

> Setup Spring Project ✅
>- [x] setup spring project with ollama dependency ✔️
>- [x] create an endpoint which prompts model with query ✔️
>- [x] test working of it ✔️

> Agentic module ✅
>- [x] setup ChatAgent - prompt models and store response with some metadata ✔️
>- [x] setup Manager - manage parallel threads to run independent 'chat agent' ✔️
>- [x] setup SimulationResult (POJO) - (Plain Old Java Object), Java object designed to hold data without much logic. || typically a class that contains fields, constructors, getters, setters, and sometimes other utility methods ✔️
>- [x] create a REST API to trigger this flow ✔️



> Aggentic Chat Simulator.
> cc **Akashay Anand**

