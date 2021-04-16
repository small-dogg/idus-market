package com.idus.market.controller;

import com.idus.market.domain.order.Orders;
import com.idus.market.service.OrdersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

  private OrdersService ordersService;

  @GetMapping
  private List<Orders> getOrders(String email) {
    return ordersService.findAllByEmail(email);
  }

}
