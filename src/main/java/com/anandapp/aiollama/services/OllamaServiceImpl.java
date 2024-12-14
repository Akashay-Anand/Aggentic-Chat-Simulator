package com.anandapp.aiollama.services;

import com.anandapp.aiollama.model.Answer;
import com.anandapp.aiollama.model.UserQuery;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class OllamaServiceImpl implements  OllamaService {

    private  final ChatModel chatModel;
    public OllamaServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Answer getAnswer(UserQuery query){
        PromptTemplate promptTemplate = new PromptTemplate(query.query());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);

//        System.out.println("🟧🟧🟧 "+response);
        return new Answer(response.getResult().getOutput().getText());
    }

//    @Override
//    public String getAnswer(String userQuery) {
//        PromptTemplate promptTemplate = new PromptTemplate(userQuery);
//        Prompt prompt = promptTemplate.create();
//        ChatResponse response = chatModel.call(prompt);
//
//        return response.getResult().getOutput().getText();
//    }
}
