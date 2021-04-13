package com.idus.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Builder
@AllArgsConstructor
//todo Renaming
public class GetUserDto {

  private TARGET target;
  private Direction orderby;
  private int page;
  private int size;
  public enum TARGET {
    username, email
  }
}

