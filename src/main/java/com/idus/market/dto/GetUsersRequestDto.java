package com.idus.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Builder
@AllArgsConstructor
public class GetUsersRequestDto {

  private String data;
  private TARGET target;
  public enum TARGET {
    username, email
  }
}

