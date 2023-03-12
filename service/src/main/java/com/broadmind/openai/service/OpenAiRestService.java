package com.broadmind.openai.service;

import com.broadmind.openai.common.OpenAiConfiguration;
import com.broadmind.openai.common.chat.ChatCompletionRequest;
import com.broadmind.openai.common.chat.ChatCompletionResponse;
import com.broadmind.openai.common.completion.CompletionRequest;
import com.broadmind.openai.common.completion.CompletionResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class OpenAiRestService {
  private static final String DEFAULT_API_BASE_URL = "https://api.openai.com";

  private static final long DEFAULT_CONNECT_TIME_OUT = 30_000;
  private static final long DEFAULT_READ_TIME_OUT = 30_000;
  private static final long DEFAULT_WRITE_TIME_OUT = 30_000;

  private final OpenAiService service;

  public OpenAiRestService(OpenAiConfiguration configuration) throws IOException {
    this(buildRestService(configuration));
  }

  public OpenAiRestService(OpenAiService openAiService) {
    this.service = openAiService;
  }

  public CompletionResponse createCompletion(CompletionRequest completionRequest) {
    return service.createCompletion(completionRequest).blockingGet();
  }

  public ChatCompletionResponse createChatCompletion(ChatCompletionRequest chatCompletionRequest) {
    return service.createChatCompletion(chatCompletionRequest).blockingGet();
  }

  public static OpenAiService buildRestService(OpenAiConfiguration configuration)
      throws IOException {
    ObjectMapper objectMapper = createObjectMapper();
    OkHttpClient httpClient = createHttpClient(configuration);
    String apiBaseUrl = System.getenv(OpenAiConfiguration.API_BASE_URL);
    apiBaseUrl =
        apiBaseUrl == null ? configuration.getApiBaseUrl(DEFAULT_API_BASE_URL) : apiBaseUrl;
    
    Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build();
    return retrofit.create(OpenAiService.class);
  }

  public static ObjectMapper createObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    return mapper;
  }

  public static OkHttpClient createHttpClient(OpenAiConfiguration configuration)
      throws IOException {
    String openaiApiKey = configuration.getOpenaiApiKey();
    if (null == openaiApiKey) {
      throw new IOException("Can not construct service without openai api key!");
    }
    Builder builder = new Builder();
    Boolean httpLogging = configuration.getHttpLogging(false);
    if (httpLogging) {
      builder.addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BODY));
    }
    builder.connectTimeout(
        configuration.getConnectTimeOut(DEFAULT_CONNECT_TIME_OUT), TimeUnit.SECONDS);
    builder.readTimeout(configuration.getReadTimeOut(DEFAULT_READ_TIME_OUT), TimeUnit.SECONDS);
    builder.writeTimeout(configuration.getWriteTimeOut(DEFAULT_WRITE_TIME_OUT), TimeUnit.SECONDS);

    builder.connectionPool(new ConnectionPool(5, 5, TimeUnit.SECONDS));
    builder.addInterceptor(
        chain -> {
          Request requestWithHeader =
              chain
                  .request()
                  .newBuilder()
                  .header("Authorization", String.format("Bearer %s", openaiApiKey))
                  .build();

          return chain.proceed(requestWithHeader);
        });
    return builder.build();
  }
}
