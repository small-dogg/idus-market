package com.idus.market.repository;

import com.idus.market.domain.order.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

  List<Orders> findAllByUserId(Long userId);
}
