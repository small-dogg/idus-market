package com.idus.market.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthDto {

  @NotBlank
  private String email;
  @NotBlank
  private String password;
}
