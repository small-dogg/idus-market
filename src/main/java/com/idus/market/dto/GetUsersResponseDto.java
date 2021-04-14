package com.idus.market.dto;

import com.idus.market.domain.order.Orders;
import com.idus.market.domain.user.GenderType;
import com.idus.market.domain.user.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetUsersResponseDto extends User {
  private String name;
  private String nick;
  private String password;
  private String phoneNumber;
  private String email;
  private GenderType gender;
  private List<Orders> orders;
}

