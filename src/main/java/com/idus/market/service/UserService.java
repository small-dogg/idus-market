package com.idus.market.service;

import com.idus.market.domain.user.User;
import com.idus.market.domain.user.UserRepository;
import com.idus.market.dto.GetUsersRequestDto;
import com.idus.market.dto.GetUsersRequestDto.TARGET;
import com.idus.market.dto.GetUsersResponseDto;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;

  public List<User> findAll(Pageable pageable,
      GetUsersRequestDto getUsersRequestDto) {
    TARGET target = getUsersRequestDto.getTarget();
    if (target != null) {
      if (target == TARGET.email) {
        return userRepository.findAllByEmailIsContaining(getUsersRequestDto.getData(), pageable);
      } else {
        return userRepository.findAllByUsernameIsContaining(getUsersRequestDto.getData(), pageable);
      }
    }
//    return userRepository.findAll(pageable).toList();
    return null;
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}
