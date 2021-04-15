package com.idus.market.repository;

import com.idus.market.domain.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String name);
  Optional<User> findByEmail(String email);

//  List<User> findAllByUsernameIsContainingWithLastOrder(String name,Pageable pageable);
//
//  List<User> findAllByEmailIsContainingWithLastOrder(String email,Pageable pageable);
}
