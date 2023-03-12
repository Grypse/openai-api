package com.broadmind.openai.service;

import com.broadmind.openai.common.chat.ChatCompletionRequest;
import com.broadmind.openai.common.chat.ChatCompletionResponse;
import com.broadmind.openai.common.completion.CompletionRequest;
import com.broadmind.openai.common.completion.CompletionResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OpenAiService {

  @POST(value = "/v1/completions")
  Single<CompletionResponse> createCompletion(@Body CompletionRequest completionRequest);

  @POST(value = "/v1/chat/completions")
  Single<ChatCompletionResponse> createChatCompletion(
      @Body ChatCompletionRequest chatCompletionRequest);
}
