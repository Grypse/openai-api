package com.broadmind.openai.common.completion;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletionRequest {
  /**
   * ID of the model to use. You can use the List models API to see all of your available models, or
   * see our Model overview for descriptions of them.
   */
  @JsonProperty(value = "model")
  private String model;

  /**
   * The prompt(s) to generate completions for, encoded as a string, array of strings, array of
   * tokens, or array of token arrays.
   *
   * <p>Note that <|endoftext|> is the document separator that the model sees during training, so if
   * a prompt is not specified the model will generate as if from the beginning of a new document.
   */
  @JsonProperty(value = "prompt")
  @Nullable
  private List<String> prompt;

  /** The suffix that comes after a completion of inserted text. */
  @JsonProperty(value = "suffix")
  @Nullable
  private String suffix;

  /**
   * The maximum number of tokens to generate in the completion.
   *
   * <p>The token count of your prompt plus max_tokens cannot exceed the model's context length.
   * Most models have a context length of 2048 tokens (except for the newest models, which support
   * 4096).
   */
  @JsonProperty(value = "max_tokens")
  @Nullable
  private Integer maxTokens;

  /**
   * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output
   * more random, while lower values like 0.2 will make it more focused and deterministic.
   *
   * <p>We generally recommend altering this or top_p but not both.
   */
  @JsonProperty(value = "temperature")
  @Nullable
  private Double temperature;

  /**
   * An alternative to sampling with temperature, called nucleus sampling, where the model considers
   * the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising
   * the top 10% probability mass are considered.
   *
   * <p>We generally recommend altering this or temperature but not both.
   */
  @JsonProperty(value = "top_p")
  @Nullable
  private Double topP;

  /** How many chat completion choices to generate for each input message. */
  @JsonProperty(value = "n")
  @Nullable
  private Integer n;

  /**
   * If set, partial message deltas will be sent, like in ChatGPT. Tokens will be sent as data-only
   * server-sent events as they become available, with the stream terminated by a data: [DONE]
   * message.
   */
  @JsonProperty(value = "stream")
  @Nullable
  private Boolean stream;

  /**
   * Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens.
   * For example, if logprobs is 5, the API will return a list of the 5 most likely tokens. The API
   * will always return the logprob of the sampled token, so there may be up to logprobs+1 elements
   * in the response.
   *
   * <p>The maximum value for logprobs is 5. If you need more than this, please contact us through
   * our Help center and describe your use case.
   */
  @JsonProperty(value = "logprobs")
  @Nullable
  private Integer logrobs;

  /** Echo back the prompt in addition to the completion */
  @JsonProperty(value = "echo")
  @Nullable
  private Boolean echo;

  /**
   * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear
   * in the text so far, increasing the model's likelihood to talk about new topics.
   *
   * <p>See more information about frequency and presence penalties:<a
   * href="https://platform.openai.com/docs/api-reference/parameter-details"></a>.
   */
  @JsonProperty(value = "presence_penalty")
  @Nullable
  private Double presencePenalty;

  /**
   * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing
   * frequency in the text so far, decreasing the model's likelihood to repeat the same line
   * verbatim.
   *
   * <p>See more information about frequency and presence penalties:<a
   * href="https://platform.openai.com/docs/api-reference/parameter-details"></a>.
   */
  @JsonProperty(value = "frequency_penalty")
  @Nullable
  private Double frequencyPenalty;

  /** Up to 4 sequences where the API will stop generating further tokens. */
  @JsonProperty(value = "stop")
  @Nullable
  private List<String> stop;

  /**
   * Generates best_of completions server-side and returns the "best" (the one with the highest log
   * probability per token). Results cannot be streamed.
   *
   * <p>When used with n, best_of controls the number of candidate completions and n specifies how
   * many to return – best_of must be greater than n.
   *
   * <p>Note: Because this parameter generates many completions, it can quickly consume your token
   * quota. Use carefully and ensure that you have reasonable settings for max_tokens and stop.
   */
  @JsonProperty(value = "best_of")
  @Nullable
  private Integer bestOf;
  
  /**
   * Modify the likelihood of specified tokens appearing in the completion.
   *
   * <p>Accepts a json object that maps tokens (specified by their token ID in the tokenizer) to an
   * associated bias value from -100 to 100. Mathematically, the bias is added to the logits
   * generated by the model prior to sampling. The exact effect will vary per model, but values
   * between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100
   * should result in a ban or exclusive selection of the relevant token.
   */
  @JsonProperty(value = "logit_bias")
  @Nullable
  private Map<String, Long> logitBias;

  /**
   * A unique identifier representing your end-user, which can help OpenAI to monitor and detect
   * abuse. Read more: <a
   * href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids"></a>
   */
  @JsonProperty(value = "user")
  @Nullable
  private String user;
}
