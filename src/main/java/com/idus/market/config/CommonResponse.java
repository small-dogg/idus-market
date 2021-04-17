package com.idus.market.config;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse {

  private String message;
  private int status;

}
