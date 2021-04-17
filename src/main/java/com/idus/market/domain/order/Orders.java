package com.idus.market.domain.order;

import com.idus.market.domain.BaseTimeEntity;
import com.idus.market.dto.OrdersDto.createOrdersDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
@Getter
@Builder
public class Orders extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String orderId;

  private String name;

  private Long userId;

  public Orders(createOrdersDto createOrdersDto) {
    this.orderId = createOrdersDto.getOrderId();
    this.name = createOrdersDto.getName();
    this.userId = createOrdersDto.getUserId();
  }
}
