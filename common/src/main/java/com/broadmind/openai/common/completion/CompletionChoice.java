package com.broadmind.openai.common.completion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompletionChoice {

  @JsonProperty(value = "index")
  private Integer index;

  @JsonProperty(value = "logprobs")
  private Integer logprobs;

  @JsonProperty(value = "text")
  private String text;

  @JsonProperty(value = "finish_reason")
  private String finishReason;
}
