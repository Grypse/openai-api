package com.broadmind.openai.common.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

  @JsonProperty(value = "role")
  private String role;

  @JsonProperty(value = "content")
  private String content;
}
