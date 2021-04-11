package com.idus.market.service;

import com.idus.market.config.auth.PrincipalDetails;
import com.idus.market.config.exception.LoginFailedException;
import com.idus.market.domain.user.User;
import com.idus.market.domain.user.UserRepository;
import com.idus.market.dto.AuthDto;
import com.idus.market.dto.UserDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Configuration
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public User join(UserDto userDto) {
    if (userDto.getRoles() == null || userDto.getRoles() == "") {
      userDto.setRoles("ROLE_USER");
    }
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    return userRepository.save(new User(userDto));
  }

  @Transactional(readOnly = true)
  public PrincipalDetails login(AuthDto authDto) {
    Optional<User> user = userRepository.findByUsername(authDto.getUsername());

    if (!user.isPresent()) {
      throw new LoginFailedException();
    }
    if (!passwordEncoder.matches(authDto.getPassword(), user.get().getPassword())) {
      throw new LoginFailedException();
    }

    return new PrincipalDetails(user.get());
  }
}

