package com.idus.market.domain.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String name);
  Optional<User> findByEmail(String email);

  //Todo : have to decision about use queryDSL or JPQL
  @Query(value = "select u.id, u.username, u.email, u.gender, u.phoneNumber, o1.name from user as u "
      + "left join orders as o1 on u.id = o1.USER_ID "
      + "left join orders as o2 on u.id = o2.USER_ID "
//      + "and o1.createAt < o2.createAt "
//      + "where o2.createAt is null"
      ,nativeQuery = true)
  List<User> findAllByUsernameIsContaining(String name,Pageable pageable);

  List<User> findAllByEmailIsContaining(String name,Pageable pageable);
}
