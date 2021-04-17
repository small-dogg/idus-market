package com.idus.market.config.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
  LOGIN_FAILED(401, "AUTHENTICATION_001", "Invalid email or password");

  private final String code;
  private final String message;
  private int status;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }
}
