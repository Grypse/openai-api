package com.broadmind.openai.common;

import java.util.HashMap;
import java.util.Map;

public class OpenAiConfiguration {
  public static final String API_BASE_URL = "API_BASE_URL";
  public static final String OPENAI_API_KEY = "OPENAI_API_KEY";
  public static final String OPENAI_API_MODEL = "OPENAI_API_MODEL";
  public static final String CONNECT_TIMEOUT = "CONNECT_TIMEOUT";

  public static final String READ_TIMEOUT = "READ_TIMEOUT";
  public static final String WRITE_TIMEOUT = "WRITE_TIMEOUT";

  public static final String HTTP_LOGGING = "HTTP_LOGGING";
  private final Map<String, Object> configs;

  public OpenAiConfiguration() {
    this.configs = new HashMap<>();
  }

  public void setApiBaseUrl(String baseUrl) {
    this.configs.put(API_BASE_URL, baseUrl);
  }

  public void setOpenaiApiKey(String openaiApiKey) {
    this.configs.put(OPENAI_API_KEY, openaiApiKey);
  }

  public void setOpenaiApiModel(String model) {
    this.configs.put(OPENAI_API_MODEL, model);
  }

  public void setConnectTimeout(long timeout) {
    this.configs.put(CONNECT_TIMEOUT, timeout);
  }

  public void setReadTimeout(long timeout) {
    this.configs.put(READ_TIMEOUT, timeout);
  }

  public void setWriteTimeout(long timeout) {
    this.configs.put(WRITE_TIMEOUT, timeout);
  }

  public void setHttpLogging(Boolean logging) {
    this.configs.put(HTTP_LOGGING, logging);
  }

  public String getApiBaseUrl() {
    return (String) this.configs.get(API_BASE_URL);
  }

  public String getApiBaseUrl(String defaultBaseUrl) {
    return (String) this.configs.getOrDefault(API_BASE_URL, defaultBaseUrl);
  }

  public String getOpenaiApiKey() {
    return (String) this.configs.get(OPENAI_API_KEY);
  }

  public String getOpenaiApiModel() {
    return (String) this.configs.get(OPENAI_API_MODEL);
  }

  public String getOpenaiApiModel(String defaultModel) {
    return (String) this.configs.getOrDefault(OPENAI_API_MODEL, defaultModel);
  }

  public Long getConnectTimeOut() {
    return (Long) this.configs.get(CONNECT_TIMEOUT);
  }

  public Long getConnectTimeOut(long defaultTimeOut) {
    return (Long) this.configs.getOrDefault(CONNECT_TIMEOUT, defaultTimeOut);
  }

  public Long getReadTimeOut() {
    return (Long) this.configs.get(READ_TIMEOUT);
  }

  public Long getReadTimeOut(long defaultTimeOut) {
    return (Long) this.configs.getOrDefault(READ_TIMEOUT, defaultTimeOut);
  }

  public Long getWriteTimeOut() {
    return (Long) this.configs.get(WRITE_TIMEOUT);
  }

  public Long getWriteTimeOut(long defaultTimeOut) {
    return (Long) this.configs.getOrDefault(WRITE_TIMEOUT, defaultTimeOut);
  }

  public Boolean getHttpLogging() {
    return (Boolean) this.configs.get(HTTP_LOGGING);
  }

  public Boolean getHttpLogging(Boolean defaultLogging) {
    return (Boolean) this.configs.getOrDefault(HTTP_LOGGING, defaultLogging);
  }
}
