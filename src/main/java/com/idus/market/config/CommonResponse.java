package com.idus.market.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse {

  private String message;
  private int status;

  @JsonCreator
  public CommonResponse(@JsonProperty("message") String message,
      @JsonProperty("status") int status) {

    this.message = message;
    this.status = status;
  }
}
