package com.idus.market.controller;

import com.idus.market.config.CommonResponse;
import com.idus.market.domain.order.Orders;
import com.idus.market.dto.OrdersDto.createOrdersDto;
import com.idus.market.service.OrdersService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

  @ApiOperation(value = "주문 생성", notes = "주문을 생성합니다")
  @ApiImplicitParam(name = "name", value = "주문 상품")
  @PostMapping
  private CommonResponse createOrders(createOrdersDto createOrdersDto) {

    ordersService.save(createOrdersDto);

    return CommonResponse.builder()
        .message("Success to order")
        .status(200)
        .build();
  }

}
