package com.idus.market.dto;

import com.idus.market.domain.BaseTimeEntity;
import com.idus.market.domain.order.Orders;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class OrdersDto {

  @Getter
  @RequiredArgsConstructor
  public static class createOrdersDto {

    @Size(max = 100, message = "제품명의 길이는 최대 100자 입니다.")
    private final String name;
    @ApiModelProperty(hidden = true)
    private String orderId;
    @ApiModelProperty(hidden = true)
    private Long userId;

    public void setOrderId(String orderId) {
      this.orderId = orderId;
    }

    public void setUserId(Long userId) {
      this.userId = userId;
    }
  }

  @Getter
  public static class getOrdersResponseDto extends BaseTimeEntity {
    private String name;
    private String orderId;

    @QueryProjection
    public getOrdersResponseDto(Orders orders){
      this.name = orders.getName();
      this.orderId = orders.getOrderId();
      this.createdAt = orders.getCreatedAt();
      this.modifiedAt = orders.getModifiedAt();
    }
  }
}
