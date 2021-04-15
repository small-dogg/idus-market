package com.idus.market.service;

import com.idus.market.domain.user.User;
import com.idus.market.repository.QUserRepository;
import com.idus.market.repository.UserRepository;
import com.idus.market.dto.UserDto.GetUsersRequestDto;
import com.idus.market.dto.UserDto.GetUsersRequestDto.TARGET;
import com.idus.market.dto.UserDto.GetUsersResponseDto;
import java.util.ArrayList;
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
  private final QUserRepository qUserRepository;

  public List<GetUsersResponseDto> findAll(Pageable pageable, GetUsersRequestDto getUsersRequestDto) {
    List<GetUsersResponseDto> usersResponseDtoList = new ArrayList<>();
    TARGET target = getUsersRequestDto.getTarget();
    if (target != null) {
      if (target == TARGET.email) {
//        qUserRepository.findAllByUsernameIsContainingWithLastOrder();
      } else {
        qUserRepository.findAllByUsernameIsContainingWithLastOrder(getUsersRequestDto.getData());
      }
    }
    return null;
//    return userRepository.findAll(pageable).toList();
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}
