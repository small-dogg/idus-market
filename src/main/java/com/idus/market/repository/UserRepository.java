package com.idus.market.repository;

import com.idus.market.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByName(String name);

  Optional<User> findByEmail(String email);
}
