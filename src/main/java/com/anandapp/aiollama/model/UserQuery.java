package com.anandapp.aiollama.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserQuery(@JsonProperty("query") String query) {
}

/*
# Notes related to "Record" is added in Answer.java file
*/
