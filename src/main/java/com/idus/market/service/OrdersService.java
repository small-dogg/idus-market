package com.idus.market.service;

import com.idus.market.config.auth.PrincipalDetails;
import com.idus.market.domain.order.Orders;
import com.idus.market.domain.user.User;
import com.idus.market.dto.OrdersDto.createOrdersDto;
import com.idus.market.repository.OrdersRepository;
import com.idus.market.repository.UserRepository;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
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


  @Transactional
  public void save(createOrdersDto createOrdersDto) {
    PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();

    createOrdersDto.setOrderId(shortUUID());
    createOrdersDto.setUserId(principalDetails.getUser().getId());
    ordersRepository.save(new Orders(createOrdersDto));
  }

  public static String shortUUID() {
    return Long.toString(ByteBuffer.wrap(UUID.randomUUID().toString().getBytes()).getLong(),
        Character.MAX_RADIX).toUpperCase();
  }

}
