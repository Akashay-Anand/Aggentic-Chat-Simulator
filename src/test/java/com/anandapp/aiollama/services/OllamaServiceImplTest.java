package com.anandapp.aiollama.services;

import com.anandapp.aiollama.model.Answer;
import com.anandapp.aiollama.model.UserQuery;
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
        UserQuery userQuery = new UserQuery("what is the capital of india");
        Answer answer = ollamaService.getAnswer(userQuery); // correct this
        System.out.println("✅✅ answer is: "+ answer.answer());
    }
}