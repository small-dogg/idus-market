package com.idus.market.domain.user;

import com.idus.market.dto.GetUsersRequestDto;
import com.idus.market.dto.GetUsersResponseDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String name);
  Optional<User> findByEmail(String email);

  //Todo : make join query
//  @Query("SELECT u FROM User u INNER JOIN u.order o")
  List<User> findAllByUsernameIsContaining(String name,Pageable pageable);
  List<User> findAllByEmailIsContaining(String name,Pageable pageable);
}
