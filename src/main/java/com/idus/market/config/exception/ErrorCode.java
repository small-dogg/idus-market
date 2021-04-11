package com.idus.market.config.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
  AUTHENTICATION_FAILED(401, "AUTHENTICATION_001", "Authentication failed"),
  LOGIN_FAILED(401, "AUTHENTICATION_002", "Invalid username or password"),
  ACCESS_DENIED(401, "AUTHENTICATION_003", "Access Denied"),
  TOKEN_GENERATION_FAILED(500, "AUTHENTICATION_004", "TOKEN_GENERATION_FAILED");


  private final String code;
  private final String message;
  private int status;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }
}
