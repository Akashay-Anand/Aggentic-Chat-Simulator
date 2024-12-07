package com.anandapp.aiollama.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserQuery(@JsonProperty("query") String query) {
}
