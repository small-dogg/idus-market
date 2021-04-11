package com.idus.market.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AuthDto {
  private String username;
  private String password;
  @ApiModelProperty(hidden=true)
  private String roles;
  @ApiModelProperty(hidden=true)
  private String token;
}
