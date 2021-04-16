package com.idus.market.service;

import com.idus.market.domain.order.Orders;
import com.idus.market.domain.user.User;
import com.idus.market.repository.OrdersRepository;
import com.idus.market.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrdersService {

  private final OrdersRepository ordersRepository;
  private final UserRepository userRepository;

  public List<Orders> findAllByEmail(String email) {

    Optional<User> user = userRepository.findByEmail(email);

    if (user.isPresent()) {
      return ordersRepository.findAllByUserId(user.get().getId());
    }
    return new ArrayList<>();
  }


}
