package com.idus.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
//todo Renaming
public class GetUserDto {

  private String username;
  private String email;
  private int page;
  private int size;
}
