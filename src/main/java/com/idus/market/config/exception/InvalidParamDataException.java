package com.idus.market.config.exception;

public class InvalidParamDataException extends RuntimeException {

  public InvalidParamDataException() {
    super(ErrorCode.INVALID_PARAM_DATA.getMessage());
  }

  private InvalidParamDataException(String msg) {
    super(msg);
  }
}
