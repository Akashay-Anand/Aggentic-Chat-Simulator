package com.anandapp.aiollama.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OllamaServiceImplTest {

    @Autowired
    OllamaService ollamaService;

    @Test
    void getAnswer() {
        String answer = ollamaService.getAnswer("write a tic tak toe game in java");
        System.out.println("answer is: "+ answer);
    }
}