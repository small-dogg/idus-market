package com.idus.market.service;

import com.idus.market.config.auth.PrincipalDetails;
import com.idus.market.config.exception.LoginFailedException;
import com.idus.market.domain.user.User;
import com.idus.market.dto.AuthDto;
import com.idus.market.dto.UserDto.CreateUserDto;
import com.idus.market.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Configuration
@Transactional(readOnly = true)
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public User join(CreateUserDto createUserDto) {
    if (createUserDto.getRoles() == null || createUserDto.getRoles() == "") {
      createUserDto.setRoles("ROLE_USER");
    }
    createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
    return userRepository.save(new User(createUserDto));
  }


  public PrincipalDetails login(AuthDto authDto) {
    Optional<User> user = userRepository.findByEmail(authDto.getEmail());

    if (!user.isPresent()) {
      throw new LoginFailedException();
    }
    if (!passwordEncoder.matches(authDto.getPassword(), user.get().getPassword())) {
      throw new LoginFailedException();
    }

    return new PrincipalDetails(user.get());
  }
}

