package com.broadmind.openai.common.chat;

import com.broadmind.openai.common.Usage;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ChatCompletionResponse {
  @JsonProperty(value = "id")
  private String id;

  @JsonProperty(value = "model")
  private String model;

  @JsonProperty(value = "object")
  private String object;

  @JsonProperty(value = "created")
  private long created;

  @JsonProperty(value = "choices")
  private List<ChatChoice> choices;

  @JsonProperty(value = "usage")
  private Usage usage;
}
