package com.broadmind.openai.common.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatChoice {
  @JsonProperty(value = "index")
  private int index;

  @JsonProperty(value = "message")
  private ChatMessage message;

  @JsonProperty(value = "finish_reason")
  private String finishReason;
}
