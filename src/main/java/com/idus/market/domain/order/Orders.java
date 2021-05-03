package com.idus.market.domain.order;

import com.idus.market.domain.BaseTimeEntity;
import com.idus.market.dto.OrdersDto.createOrdersDto;
import javax.persistence.Column;
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

  @Column(length = 12, nullable = false)
  private String orderId;

  @Column(length = 100, nullable = false)
  private String name;

  @Column(nullable = false)
  private Long userId;

  public Orders(createOrdersDto createOrdersDto) {
    this.orderId = createOrdersDto.getOrderId();
    this.name = createOrdersDto.getName();
    this.userId = createOrdersDto.getUserId();
  }
}
