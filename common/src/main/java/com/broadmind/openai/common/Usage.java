package com.broadmind.openai.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Usage {

  @JsonProperty(value = "prompt_tokens")
  private long promptTokens;

  @JsonProperty(value = "completion_tokens")
  private long completionTokens;

  @JsonProperty(value = "total_tokens")
  private long totalTokens;
}
