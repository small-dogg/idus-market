package com.idus.market.service;

import com.idus.market.config.auth.PrincipalDetails;
import com.idus.market.config.exception.InvalidParamDataException;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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
    if (createOrdersDto.getName().length() > 100) {
      throw new InvalidParamDataException();
    }

    PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();

    createOrdersDto.setOrderId(shortUUID());
    createOrdersDto.setUserId(principalDetails.getUser().getId());

    log.info("A new order has been registered : {}", createOrdersDto.getOrderId());
    ordersRepository.save(new Orders(createOrdersDto));
  }

  public static String shortUUID() {
    while(true){
      String uuid = Long.toString(ByteBuffer.wrap(UUID.randomUUID().toString().getBytes()).getLong(),
          Character.MAX_RADIX).toUpperCase();
      if(uuid.length()==12)return uuid;
    }
//    return Long.toString(ByteBuffer.wrap(UUID.randomUUID().toString().getBytes()).getLong(),
//        Character.MAX_RADIX).toUpperCase();
  }

}
