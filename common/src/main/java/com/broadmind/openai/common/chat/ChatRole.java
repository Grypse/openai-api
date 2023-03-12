package com.broadmind.openai.common.chat;

public enum ChatRole {
  SYSTEM("system"),
  USER("user"),
  ASSISTANT("assistant");

  private final String name;

  ChatRole(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
