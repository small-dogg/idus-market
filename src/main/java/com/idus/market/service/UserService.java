package com.idus.market.service;

import com.idus.market.domain.user.User;
import com.idus.market.domain.user.UserRepository;
import com.idus.market.dto.GetUserDto;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;


  public List<User> findAll() {
    return userRepository.findAll();
  }

  public Page<User> findAll(GetUserDto getUserDto) {
    return userRepository.findAll(
        PageRequest.of(getUserDto.getPage(), getUserDto.getSize(), getUserDto.getOrderby(),
            getUserDto.getTarget().toString()));
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}
