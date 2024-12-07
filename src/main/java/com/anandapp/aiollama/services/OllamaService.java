package com.anandapp.aiollama.services;

import com.anandapp.aiollama.model.Answer;
import com.anandapp.aiollama.model.UserQuery;

public interface OllamaService {

//    String getAnswer(String userQuery);

    Answer getAnswer(UserQuery query);
}
