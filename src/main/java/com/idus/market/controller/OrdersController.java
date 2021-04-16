package com.idus.market.controller;

import com.idus.market.domain.order.Orders;
import com.idus.market.service.OrdersService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

  private final OrdersService ordersService;

  @ApiOperation(value = "단일 회원의 주문 목록 조회", notes = "단일 회원의 주문 목록을 조회합니다")
  @ApiImplicitParam(name = "email", value = "이메일")
  @GetMapping
  private List<Orders> getOrders(String email) {
    return ordersService.findAllByEmail(email);
  }

}
